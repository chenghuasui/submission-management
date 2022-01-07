/*
*   
*/
package top.mingde.common.oauth.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mingde.common.oauth.entity.ClientDetails;
import top.mingde.common.oauth.mapper.IClientDetailsMapper;
import top.mingde.common.oauth.service.IClientDetailsService;
import top.mingde.tool.core.mybatis.BaseServiceImpl;

/**
 * <p>
 * 客户端信息 服务实现类
 * </p>
 *
 */
@Service
@Transactional(readOnly = true)
@Slf4j
@AllArgsConstructor
public class ClientDetailsServiceImpl
    extends BaseServiceImpl<IClientDetailsMapper, ClientDetails> implements IClientDetailsService {

}
