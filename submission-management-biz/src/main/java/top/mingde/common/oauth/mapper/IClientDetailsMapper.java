/*
*   
*/
package top.mingde.common.oauth.mapper;


import org.apache.ibatis.annotations.Mapper;
import top.mingde.common.oauth.entity.ClientDetails;
import top.mingde.tool.core.mybatis.BaseMapper;

/**
 * <p>
 * 客户端信息 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface IClientDetailsMapper extends BaseMapper<ClientDetails> {

}
