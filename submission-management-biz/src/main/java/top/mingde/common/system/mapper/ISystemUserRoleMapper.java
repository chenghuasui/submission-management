package top.mingde.common.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.mingde.common.system.entity.SystemUserRole;
import top.mingde.tool.core.mybatis.BaseMapper;

/**
 * <p>
 * 系统用户角色表 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface ISystemUserRoleMapper extends BaseMapper<SystemUserRole> {

}
