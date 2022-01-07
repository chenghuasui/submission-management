package top.mingde.common.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.mingde.common.system.entity.SystemRolePermi;
import top.mingde.tool.core.mybatis.BaseMapper;

/**
 * <p>
 * 系统角色权限表 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface ISystemRolePermiMapper extends BaseMapper<SystemRolePermi> {

}
