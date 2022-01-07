package top.mingde.common.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mingde.common.system.entity.SystemPermi;
import top.mingde.common.system.entity.SystemRolePermi;
import top.mingde.common.system.entity.SystemUserRole;
import top.mingde.common.system.mapper.ISystemPermiMapper;
import top.mingde.common.system.service.ISystemPermiService;
import top.mingde.common.system.service.ISystemRolePermiService;
import top.mingde.common.system.service.ISystemUserRoleService;
import top.mingde.tool.core.mybatis.BaseServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统权限表 服务实现类
 * </p>
 *
 * 
 * @since 2019-04-04
 */
@Service("systemPermiService")
@Transactional
@AllArgsConstructor
public class SystemPermiServiceImpl
		extends BaseServiceImpl<ISystemPermiMapper, SystemPermi>
		implements ISystemPermiService {

	private final ISystemUserRoleService systemUserRoleService;
	private final ISystemRolePermiService systemRolePermiService;

	@Override
	public List<SystemPermi> getPermissions(Long userId, boolean superFlag) {
		List<SystemPermi> permiList = Lists.newArrayList();
		if(superFlag) {
			permiList = baseMapper.selectList(Wrappers.query(new SystemPermi()));
		} else {
			QueryWrapper<SystemUserRole> wrapper = new QueryWrapper<>();
			wrapper.lambda().eq(SystemUserRole::getUserId, userId);
			List<SystemUserRole> userRoleList = systemUserRoleService.list(wrapper);
			if(ObjectUtil.isNotEmpty(userRoleList)){
				List<Integer> roleIdList = userRoleList.stream()
						.map(u -> u.getRoleId()).collect(Collectors.toList());
				QueryWrapper<SystemRolePermi> rolePermiWrapper = new QueryWrapper<SystemRolePermi>();
				rolePermiWrapper.lambda().in(SystemRolePermi::getRoleId, roleIdList);
				List<SystemRolePermi> rolePermiList = systemRolePermiService.list(rolePermiWrapper);
				List<Integer> permiIdList = rolePermiList.stream().map(element->element.getPermiId())
						.collect(Collectors.toList());
				if(permiIdList.size() > 0){
					permiList = baseMapper.selectBatchIds(permiIdList);
					permiList = permiList.stream()
							.filter(p -> ObjectUtil.isNotNull(p.getMenuId()))
							.distinct()
							.collect(Collectors.toList());
				}
			}
		}
		return permiList;
	}

	@Override
	public boolean hashRepeatPermisData(List<SystemPermi> permiList) {
		boolean flag = Boolean.TRUE;
		flag = (permiList.size() == permiList.stream().collect(
				Collectors.collectingAndThen(
						Collectors.toCollection(() ->
								new TreeSet<>(Comparator.comparing(n -> StrUtil.concat(true,n.getUrl(),";",n.getMethod())))),
						ArrayList<SystemPermi>::new)
		).stream().count());
		if(flag){
			flag = (permiList.size() == (permiList.stream().map(permi -> permi.getPermission())
					.collect(Collectors.toList()).stream().distinct().count()));
		}
		return !flag;

	}


}
