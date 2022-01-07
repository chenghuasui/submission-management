/*
*   
*/
package top.mingde.common.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.mingde.common.system.entity.SystemUser;
import top.mingde.tool.core.mybatis.BaseMapper;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface ISystemUserMapper extends BaseMapper<SystemUser> {

}
