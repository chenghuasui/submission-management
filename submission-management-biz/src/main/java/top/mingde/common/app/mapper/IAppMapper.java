/*
*   
*/
package top.mingde.common.app.mapper;


import org.apache.ibatis.annotations.Mapper;
import top.mingde.common.app.entity.App;
import top.mingde.tool.core.mybatis.BaseMapper;

/**
 * <p>
 * 应用信息表 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface IAppMapper extends BaseMapper<App> {

}
