package top.mingde.common.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mingde.common.system.entity.SystemUserRole;
import top.mingde.common.system.mapper.ISystemUserRoleMapper;
import top.mingde.common.system.service.ISystemUserRoleService;
import top.mingde.tool.core.mybatis.BaseServiceImpl;

/**
 * <p>
 * 系统用户角色表 服务实现类
 * </p>
 *
 * 
 * @since 2019-07-30
 */
@Service("systemUserRoleService")
@Transactional
public class SystemUserRoleServiceImpl extends BaseServiceImpl<ISystemUserRoleMapper, SystemUserRole>
	implements ISystemUserRoleService {

}
