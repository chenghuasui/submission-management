package top.mingde.common.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.mingde.common.system.entity.SystemRole;
import top.mingde.tool.core.mybatis.BaseMapper;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface ISystemRoleMapper extends BaseMapper<SystemRole> {

}
