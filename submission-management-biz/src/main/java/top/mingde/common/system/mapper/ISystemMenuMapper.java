package top.mingde.common.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.mingde.common.system.entity.SystemMenu;
import top.mingde.tool.core.mybatis.BaseMapper;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * 
 * @since 2019-07-30
 */
@Mapper
public interface ISystemMenuMapper extends BaseMapper<SystemMenu> {
	
}
