package top.mingde.common.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mingde.common.system.entity.SystemMenu;
import top.mingde.common.system.entity.SystemPermi;
import top.mingde.common.system.mapper.ISystemMenuMapper;
import top.mingde.common.system.model.dto.SystemMenuTree;
import top.mingde.common.system.model.dto.SystemMenuWithPermis;
import top.mingde.common.system.model.vo.AuthMenuTreeNode;
import top.mingde.common.system.model.vo.MenuTreeNode;
import top.mingde.common.system.service.ISystemMenuService;
import top.mingde.common.system.service.ISystemPermiService;
import top.mingde.model.enums.DelFlagEnum;
import top.mingde.security.utils.AuthUtil;
import top.mingde.tool.core.mybatis.BaseServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * 
 * @since 2019-07-30
 */
@Service("systemMenuService")
@Transactional(rollbackFor=Exception.class)
@AllArgsConstructor
public class SystemMenuServiceImpl extends BaseServiceImpl<ISystemMenuMapper, SystemMenu>
	implements ISystemMenuService {

	private final ISystemPermiService permiService;
	private final ISystemPermiService systemPermiService;

	@Override
	public List<SystemMenu> getMenuListByAuthUser(Long userId, boolean superFlag) {
		List<SystemMenu> menuList = Lists.newArrayList();
		QueryWrapper<SystemMenu> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(SystemMenu::getDelFlag, DelFlagEnum.NORMAL.getValue());
		wrapper.lambda().orderByAsc(SystemMenu::getSort);
		List<SystemMenu> allMenuList = baseMapper.selectList(wrapper);
		if (superFlag) {
			menuList.addAll(allMenuList);
		} else {
			List<SystemPermi> permiList = permiService.getPermissions(userId, superFlag);
			List<Integer> menuIdList = new ArrayList<>();
//			//添加顶级菜单
//			menuIdList.add(new Integer(1));
			//添加权限内包含的菜单
			if(ObjectUtil.isNotEmpty(permiList)) {
				menuIdList.addAll(permiList.stream()
						.map(m -> m.getMenuId())
						.distinct()
						.collect(Collectors.toList()));
			}
			if(ObjectUtil.isNotEmpty(menuIdList)&&menuIdList.size()>0) {
				wrapper.lambda().eq(SystemMenu::getId, menuIdList);
				List<SystemMenu> basisMenus = baseMapper.selectBatchIds(menuIdList);
				basisMenus.forEach(menu -> {
					menuList.addAll(this.setParentMenus(menu, allMenuList, menuList));
				});

			}
		}
		return this.removeDuplicateMenu(menuList);
	}

	/**
	 * 设置父级菜单
	 * @param menu
	 * @param allMenuList
	 * @param menuList
	 * @return
	 */
	private List<SystemMenu> setParentMenus(SystemMenu menu, List<SystemMenu> allMenuList, List<SystemMenu> menuList){
		if(!menuList.contains(menu)){
			menuList.add(menu);
		}
		menu = hashParentMenu(menu,allMenuList);
		if(ObjectUtil.isNotNull(menu) && menu.getLevel() != 0){
			menuList.addAll(setParentMenus(menu, allMenuList, menuList));
		}
		return removeDuplicateMenu(menuList);
	}

	/**
	 * 是否还有父级菜单
	 * @param menu
	 * @param allMenuList
	 * @return
	 */
	private SystemMenu hashParentMenu(SystemMenu menu,List<SystemMenu> allMenuList){
		SystemMenu parentMenu = null;
		if(ObjectUtil.isNotEmpty(allMenuList) && allMenuList.size() > 0){
			for (SystemMenu systemMenu : allMenuList) {
				if((ObjectUtil.isNotNull(menu.getParentId())?menu.getParentId() : 0) == systemMenu.getId()){
					parentMenu = systemMenu;
					break;
				}
			}
		}
		return parentMenu;
	}

	/**
	 * 去重
	 * @param menuList
	 * @return
	 */
	private List<SystemMenu> removeDuplicateMenu(List<SystemMenu> menuList){
		return menuList.stream().collect(
				Collectors.collectingAndThen(
						Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(SystemMenu::getId))),
						ArrayList<SystemMenu>::new));
	}


	@Override
	public boolean saveMenuAndPermis(SystemMenuWithPermis systemMenuAndPermiList) {
		SystemMenu systemMenu = JSON.parseObject(JSON.toJSONString(systemMenuAndPermiList),SystemMenu.class);
		boolean flag = super.save(systemMenu);
		if(flag){
			//保存主明细
			SystemPermi mainPermi = new SystemPermi();
			mainPermi.setName(systemMenu.getName());
			mainPermi.setText(systemMenu.getName());
			mainPermi.setCode(systemMenu.getSign());
			mainPermi.setMenuId(systemMenu.getId());
			mainPermi.setPermission(systemMenu.getSign());
			mainPermi.setSort(0);
			permiService.save(mainPermi);
			//保存明细
			List<SystemPermi> permiList = systemMenuAndPermiList.getPermissionList();
			if(ObjectUtil.isNotEmpty(permiList.size())){
				int i = 0;
				for (SystemPermi permi: permiList) {
					permi.setName(StrUtil.concat(true,systemMenu.getName(),"-",permi.getText()));
					permi.setParentId(mainPermi.getId());
					permi.setMenuId(mainPermi.getMenuId());
					permi.setEvent(permi.getPermission());
					permi.setPermission(StrUtil.concat(true,systemMenu.getSign(),"_",permi.getPermission()));
					permi.setCode(permi.getPermission());
					i++;
					permi.setSort(i);
				}
				permiService.saveBatch(permiList);
			}
		}
		return flag;
	}

	@Override
	public boolean updateMenuAndPermis(SystemMenuWithPermis systemMenuAndPermiList) {
		SystemMenu systemMenu = JSON.parseObject(JSON.toJSONString(systemMenuAndPermiList),SystemMenu.class);
		boolean flag = super.updateById(systemMenu);
		if(flag){
			//0 获取传入的权限标识列表(传参)
			List<String> permis = systemMenuAndPermiList.getPermissionList().stream()
					.map(permi->permi.getPermission())
					.collect(Collectors.toList());


			//1.找出列表中所有
			QueryWrapper<SystemPermi> allPermiWrapper = new QueryWrapper<>();
			allPermiWrapper.lambda().eq(SystemPermi::getMenuId, systemMenuAndPermiList.getId());
			allPermiWrapper.lambda().isNotNull(SystemPermi::getParentId);
			allPermiWrapper.lambda().ne(SystemPermi::getPermission,systemMenuAndPermiList.getSign());
			List<SystemPermi> allPermiList = permiService.list(allPermiWrapper);

			//2.比较，找出在数据库列表禁用的并且在传参中，有则释放、更新
			List<SystemPermi> recoverPermiList = allPermiList.stream()
					.filter(permi-> StrUtil.equals(permi.getDelFlag(), DelFlagEnum.DISABLE.getValue()))
					.collect(Collectors.toList());
			if(recoverPermiList.size() > 0){
				recoverPermiList = recoverPermiList.stream().
						filter(permi -> permis.contains(permi.getEvent()))
						.collect(Collectors.toList());
				if(recoverPermiList.size() > 0){
					//执行批量更新
					recoverPermiList.forEach(recoverPermi -> {
						systemMenuAndPermiList.getPermissionList().forEach(paramPermi ->{
							if(StrUtil.equals(paramPermi.getPermission(),recoverPermi.getEvent())){
								recoverPermi.setUrl(recoverPermi.getUrl());
								recoverPermi.setMethod(recoverPermi.getMethod());
							}
						});
						recoverPermi.setDelFlag(DelFlagEnum.NORMAL.getValue());
					});
					permiService.updateBatchById(recoverPermiList);
				}
			}

			//3.再比较，找出在数据库列表中并且在传参中的并且权限标识正常的更新URL 和 method
			List<SystemPermi> updatePermiList = allPermiList.stream()
					.filter(permi->permis.contains(permi.getEvent()))
					.collect(Collectors.toList());
			if(updatePermiList.size() > 0){
				updatePermiList.forEach(updatePermis -> {
					systemMenuAndPermiList.getPermissionList().forEach(paramPermi ->{
						if(StrUtil.equals(paramPermi.getPermission(),updatePermis.getEvent())){
							updatePermis.setUrl(paramPermi.getUrl());
							updatePermis.setMethod(paramPermi.getMethod());
						}
					});
				});
				permiService.updateBatchById(updatePermiList);
			}


			//4.再比较，找出在数据库列表中并且不存在传参中的，有则禁止
			List<SystemPermi> disablePermiList = allPermiList.stream()
					.filter(permi->!permis.contains(permi.getEvent()))
					.collect(Collectors.toList());
			if(disablePermiList.size() > 0){
				disablePermiList.forEach(updatePermis -> {
					updatePermis.setDelFlag(DelFlagEnum.DISABLE.getValue());
				});
				permiService.updateBatchById(disablePermiList);
			}



			//5.再比较，找出存在传参中并且不存在数据列表中的，有则存储
			List<String> dataPermis = allPermiList.stream()
					.map(m->m.getEvent()).collect(Collectors.toList());

			List<String> insertPermiStrList = permis.stream()
					.filter(permi ->!dataPermis.contains(permi))
					.collect(Collectors.toList());

			if(insertPermiStrList.size() > 0){
				List<SystemPermi> insertPermiList = systemMenuAndPermiList.getPermissionList().stream()
						.filter(permi -> insertPermiStrList.contains(permi.getPermission()))
						.collect(Collectors.toList());
				SystemPermi usablePermi = allPermiList.get(allPermiList.size()-1);
				int i = usablePermi.getSort();
				for (SystemPermi permi: insertPermiList) {
					permi.setName(StrUtil.concat(true,systemMenu.getName(),"-",permi.getText()));
					permi.setParentId(usablePermi.getParentId());
					permi.setMenuId(usablePermi.getMenuId());
					permi.setEvent(permi.getPermission());
					permi.setPermission(StrUtil.concat(true,systemMenu.getSign(),"_",permi.getPermission()));
					permi.setCode(permi.getPermission());
					i++;
					permi.setSort(i);
				}
				permiService.saveBatch(insertPermiList);
			}
		}
		return flag;
	}

	/**
	 * 获取角色页面中展示的所有菜单树
	 *
	 * 此方法使用menu_的意思是，菜单的id和权限id都是int类型，可能会相同，导致hutoolTree控件中递归无限调用，所以菜单id上加了menu_前缀区分，
	 * 同时也方便前端树控件展示。
	 * @return
	 */
	@Override
	public List<Tree<String>> getAuthMenuList() {
		QueryWrapper<SystemMenu> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(SystemMenu::getDelFlag, DelFlagEnum.NORMAL.getValue());
		wrapper.lambda().orderByAsc(SystemMenu::getSort);
		List<SystemMenu> menuList = baseMapper.selectList(wrapper);
		List<AuthMenuTreeNode> menuTreeList = CollUtil.newArrayList();
		AuthMenuTreeNode menuTree = null;
		for (SystemMenu menu : menuList) {
			menuTree = new AuthMenuTreeNode(
					"menu_"+Convert.toStr(menu.getId()),
					"menu_"+Convert.toStr(menu.getParentId()),
					menu.getName(),
					menu.getSort(),
					menu.getCode(),
					menu.getIcon(),
					menu.getPath(),
					menu.getComponent(),
					StrUtil.EMPTY,
					menu.getKeepAlive(),
					menu.getRequestable(),
					menu.getLevel(),
					menu.getDelFlag()
			);
			menuTreeList.add(menuTree);
		}

		//加上按钮级别
		QueryWrapper<SystemPermi> permiWrapper = new QueryWrapper<>();
		permiWrapper.lambda().like(SystemPermi::getDelFlag, DelFlagEnum.NORMAL.getValue()); //非禁用状态的
		List<SystemPermi> mainPermiList = systemPermiService.list(permiWrapper);
		for (SystemPermi systemPermi : mainPermiList) {
			menuTree = new AuthMenuTreeNode(
					Convert.toStr(systemPermi.getId()),
					"menu_"+Convert.toStr(systemPermi.getMenuId()),
					systemPermi.getText(),
					systemPermi.getSort(),
					Convert.toStr(systemPermi.getId()),
					StrUtil.EMPTY,
					StrUtil.EMPTY,
					StrUtil.EMPTY,
					StrUtil.EMPTY,
					StrUtil.EMPTY,
					StrUtil.EMPTY,
					null,
					systemPermi.getDelFlag()
			);
			menuTreeList.add(menuTree);
		}
		TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
		return TreeUtil.build(menuTreeList, "menu_1", treeNodeConfig, (treeNode, tree) -> {
			tree.setId(treeNode.getId());
			tree.setParentId(treeNode.getParentId());
			tree.setName(treeNode.getName());
		});
	}

	/**
	 * 递归
	 * @param parentMenu
	 * @param menuList
	 */
	private void setChildMenus(SystemMenuTree parentMenu, List<SystemMenuTree> menuList){
		List<SystemMenuTree> childMenus = hasChildMenus(parentMenu.getId(), menuList);
		if( childMenus.size() > 0){
			parentMenu.setChildren(childMenus);
			for (SystemMenuTree systemMenu : childMenus) {
				setChildMenus(systemMenu, menuList);
			}
		}
	}

	/**
	 * 是否还有子菜单
	 * @param id
	 * @param menuList
	 * @return
	 */
	private List<SystemMenuTree> hasChildMenus(Integer id, List<SystemMenuTree> menuList){
		List<SystemMenuTree> list = Lists.newArrayList();
		if(menuList.size() > 0 && id != null){
			for (SystemMenuTree systemMenuAndChildren : menuList) {
				System.out.println(id.intValue() +"-----"+ systemMenuAndChildren.getParentId().intValue());
				if(id.intValue() == systemMenuAndChildren.getParentId().intValue()){
					list.add(systemMenuAndChildren);
				}
			}
		}
		return list;
	}
	@Override
	public Map<Integer, List<SystemPermi>> getAuthoritiesMap() {
		List<SystemPermi> list=permiService.getPermissions(AuthUtil.getUserId(),
				AuthUtil.getSuperFlag());
		return list.stream().collect(Collectors.toMap(SystemPermi::getMenuId,
				p -> {
					List<SystemPermi> getNameList = new ArrayList<>();
					getNameList.add(p);
					return getNameList;
				},
				(List<SystemPermi> value1, List<SystemPermi> value2) -> {
					value1.addAll(value2);
					return value1;
				}
		));
	}

	/**
	 * 获取登陆时左侧菜单树，根据权限展示
	 *
	 * @return
	 */
	@Override
	public List<Tree<Integer>> getMenuTreeNodes() {
		List<SystemMenu> menuList = this.getMenuListByAuthUser(AuthUtil.getUserId(),
				AuthUtil.getSuperFlag());
		List<MenuTreeNode> menuTreeList = Lists.newArrayList();
		MenuTreeNode menuTree = null;
		for (SystemMenu menu : menuList) {
			menuTree = new MenuTreeNode(
					menu.getId(),
					ObjectUtil.isNotNull(menu.getParentId()) ? menu.getParentId() : 0,
					menu.getName(),
					menu.getSort(),
					menu.getCode(),
					menu.getIcon(),
					menu.getPath(),
					menu.getComponent(),
					StrUtil.EMPTY,
					menu.getKeepAlive(),
					menu.getRequestable(),
					menu.getLevel(),
					menu.getDelFlag()
			);
			menuTreeList.add(menuTree);
		}
		TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
		return TreeUtil.build(menuTreeList, 1, treeNodeConfig, (treeNode, tree) -> {
			tree.setId(treeNode.getId());
			tree.setParentId(treeNode.getParentId());
			tree.setWeight(treeNode.getWeight());
			tree.setName(treeNode.getName());
			tree.putExtra("code", treeNode.getCode());
			tree.putExtra("type", treeNode.getType());
			tree.putExtra("path", treeNode.getPath());
			tree.putExtra("component", treeNode.getComponent());
			tree.putExtra("authority", treeNode.getAuthority());
			tree.putExtra("keepAlive", treeNode.getKeepAlive());
			tree.putExtra("icon", treeNode.getIcon());
			tree.putExtra("level", treeNode.getLevel());
			tree.putExtra("visible", treeNode.getVisible());
		});
	}


}
