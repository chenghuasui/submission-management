package top.mingde.common.system.service;


import cn.hutool.core.lang.tree.Tree;
import top.mingde.common.system.entity.SystemMenu;
import top.mingde.common.system.entity.SystemPermi;
import top.mingde.common.system.model.dto.SystemMenuWithPermis;
import top.mingde.tool.core.mybatis.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * 
 * @since 2019-07-30
 */
public interface ISystemMenuService extends BaseService<SystemMenu> {

	/**
	 * 根据当前用户获取菜单列表
	 * @param userId
	 * @return
	 */
	List<SystemMenu> getMenuListByAuthUser(Long userId, boolean superFlag);

	/**
	 * 批量新增菜单和权限
	 * @param systemMenuAndPermiList
	 * @return
	 */
	boolean saveMenuAndPermis(SystemMenuWithPermis systemMenuAndPermiList);

	/**
	 * 批量更新菜单和权限
	 * @param systemMenuAndPermiList
	 * @return
	 */
	boolean updateMenuAndPermis(SystemMenuWithPermis systemMenuAndPermiList);

	/**
	 * 获取角色页面中展示的所有菜单树
	 * @return
	 */
	List<Tree<String>> getAuthMenuList();
	/**
	 * 获取当前用户的权限标识
	 *
	 * @return
	 */
	Map<Integer, List<SystemPermi>> getAuthoritiesMap();

	/**
	 * 获取登陆时左侧菜单树，根据权限展示
	 *
	 * @return
	 */
	List<Tree<Integer>> getMenuTreeNodes();
}
