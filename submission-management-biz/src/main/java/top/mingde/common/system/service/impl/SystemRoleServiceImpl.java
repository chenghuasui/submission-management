package top.mingde.common.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mingde.common.system.entity.SystemRole;
import top.mingde.common.system.entity.SystemRolePermi;
import top.mingde.common.system.entity.SystemUserRole;
import top.mingde.common.system.mapper.ISystemRoleMapper;
import top.mingde.common.system.model.vo.SystemRoleWithPermis;
import top.mingde.common.system.service.ISystemRolePermiService;
import top.mingde.common.system.service.ISystemRoleService;
import top.mingde.common.system.service.ISystemUserRoleService;
import top.mingde.model.enums.DelFlagEnum;
import top.mingde.tool.core.instance.ConstVal;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * 
 * @since 2019-07-30
 */
@Service("systemRoleService")
@Transactional(rollbackFor=Exception.class)
@AllArgsConstructor
public class SystemRoleServiceImpl extends ServiceImpl<ISystemRoleMapper, SystemRole>
	implements ISystemRoleService {

	private final ISystemRolePermiService rolePermiService;
	private final ISystemUserRoleService systemUserRoleService;


	@Override
	public List<String> getAuthRoles(String userId, String userType, String tenantId) {
		List<SystemRole> roleList = Lists.newArrayList();
		if(StrUtil.equals(userType, ConstVal.USER_ADMIN)) {
			roleList = getBaseMapper().selectList(
					new LambdaQueryWrapper<SystemRole>()
							.eq(SystemRole::getDelFlag, DelFlagEnum.NORMAL.getValue()));
		} else {
			QueryWrapper<SystemUserRole> wrapper = new QueryWrapper<>();
			wrapper.lambda().eq(SystemUserRole::getUserId, userId);
			List<SystemUserRole> userRoleList = systemUserRoleService.list(wrapper);
			if(ObjectUtil.isNotEmpty(userRoleList)){
				roleList = getBaseMapper().selectList(
						new LambdaQueryWrapper<SystemRole>()
						.eq(SystemRole::getDelFlag, DelFlagEnum.NORMAL.getValue())
						.in(SystemRole::getId, userRoleList.stream()
								.map(element->element.getRoleId())
								.collect(Collectors.toList())));
			}
		}
		if(ObjectUtil.isNotEmpty(roleList)){
			return roleList.stream()
					.map(element -> StrUtil.toString(element.getId()))
					.collect(Collectors.toList());
		} else {
			return Lists.newArrayList();
		}
	}

	@Override
	public boolean saveRolePermis(SystemRoleWithPermis systemRoleWithPermis) {

		//清除缓存
		QueryWrapper<SystemUserRole> userRoleQueryWrapper = new QueryWrapper<>();
		userRoleQueryWrapper.lambda().eq(SystemUserRole::getRoleId, systemRoleWithPermis.getId());
		List<SystemUserRole> userRoleList = systemUserRoleService.list(
				new LambdaQueryWrapper<SystemUserRole>().eq(SystemUserRole::getRoleId, systemRoleWithPermis.getId()));
		if(ObjectUtil.isNotEmpty(userRoleList)) {
			for (SystemUserRole userRole : userRoleList) {
				//systemMenuService.clearAuthMenuCache(userRole.getUserId());
				//systemPermiService.clearAuthPermisCache(userRole.getUserId());
			}
		}

			boolean flag = false;
		//删除
		QueryWrapper<SystemRolePermi> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(SystemRolePermi::getRoleId,systemRoleWithPermis.getId());
		flag = rolePermiService.remove(wrapper);
		//增减
		SystemRolePermi rolePermi = null;
		List<SystemRolePermi> rolePermiList = Lists.newArrayList();
		for (String permiId: systemRoleWithPermis.getPermiIds()){
			rolePermi = new SystemRolePermi();
			rolePermi.setPermiId(Convert.toInt(permiId));
			rolePermi.setRoleId(systemRoleWithPermis.getId());
			rolePermiList.add(rolePermi);
		}
		if (rolePermiList.size() > 0) {
			flag = rolePermiService.saveBatch(rolePermiList);
		}
		return flag;
	}

	/**
	 * 禁用/启用角色
	 *
	 * @param id
	 * @param delFlag
	 * @return
	 */
	@Override
	public boolean remove(Integer id, String delFlag) {
		SystemRole systemRole = super.getById(id);
		systemRole.setDelFlag(delFlag);
		if (DelFlagEnum.DISABLE.getValue().equals(delFlag)) {
			systemUserRoleService.remove(new LambdaQueryWrapper<SystemUserRole>().eq(SystemUserRole::getRoleId, id));
		}
		return super.updateById(systemRole);
	}

}
