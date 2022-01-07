package top.mingde.common.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mingde.common.system.entity.SystemRolePermi;
import top.mingde.common.system.mapper.ISystemRolePermiMapper;
import top.mingde.common.system.service.ISystemRolePermiService;
import top.mingde.tool.core.mybatis.BaseServiceImpl;

/**
 * <p>
 * 系统角色权限表 服务实现类
 * </p>
 *
 * 
 * @since 2019-07-30
 */
@Service("systemRolePermiService")
@Transactional
public class SystemRolePermiServiceImpl
		extends BaseServiceImpl<ISystemRolePermiMapper, SystemRolePermi>
	implements ISystemRolePermiService {

}
