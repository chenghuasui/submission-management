/*
*   
*/
package top.mingde.common.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mingde.common.app.entity.App;
import top.mingde.common.app.mapper.IAppMapper;
import top.mingde.common.app.service.IAppService;
import top.mingde.common.oauth.entity.ClientDetails;
import top.mingde.common.oauth.service.IClientDetailsService;
import top.mingde.model.enums.MethodEventsEnum;
import top.mingde.security.utils.PasswordUtil;
import top.mingde.tool.core.mybatis.BaseServiceImpl;

/**
 * <p>
 * 应用信息表 服务实现类
 * </p>
 *
 */
@Service
@Transactional(readOnly = true)
@Slf4j
@AllArgsConstructor
public class AppServiceImpl
    extends BaseServiceImpl<IAppMapper, App> implements IAppService {

    private final IClientDetailsService clientDetailsService;

    @Override
    public boolean checkAppNameRepeat(String appName, String event, Long id){
        if(StrUtil.equals(event, MethodEventsEnum.ADD.getValue())){
            return 0 == super.count(new LambdaQueryWrapper<App>()
                    .eq(App::getAppName, appName));
        } else if(StrUtil.equals(event, MethodEventsEnum.UPDATE.getValue())){
            return 0 == super.count(new LambdaQueryWrapper<App>()
                    .eq(App::getAppName, appName).ne(App::getId, id));
        }
        return Boolean.FALSE;
    }

    @Override
    public boolean checkAppCodeRepeat(String appCode, String event, Long id){
        if(StrUtil.equals(event, MethodEventsEnum.ADD.getValue())){
            return 0 == super.count(new LambdaQueryWrapper<App>()
                    .eq(App::getAppCode, appCode));
        } else if(StrUtil.equals(event, MethodEventsEnum.UPDATE.getValue())){
            return 0 == super.count(new LambdaQueryWrapper<App>()
                    .eq(App::getAppCode, appCode).ne(App::getId, id));
        }
        return Boolean.FALSE;
    }

    @Override
    public boolean checkClientIdRepeat(String clientId, String event, Long id){
        if(StrUtil.equals(event, MethodEventsEnum.ADD.getValue())){
            return 0 == super.count(new LambdaQueryWrapper<App>()
                    .eq(App::getClientId, clientId));
        } else if(StrUtil.equals(event, MethodEventsEnum.UPDATE.getValue())){
            return 0 == super.count(new LambdaQueryWrapper<App>()
                    .eq(App::getClientId, clientId).ne(App::getId, id));
        }
        return Boolean.FALSE;
    }

    @Transactional
    @Override
    public void createApp(App app) {
        super.save(app);
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setClientId(app.getClientId());
        clientDetails.setResourceIds("");
        clientDetails.setClientSecret(PasswordUtil.get().encode(app.getClientSecret()));
        clientDetails.setScope(app.getClientScope());
        clientDetails.setAuthorizedGrantTypes(app.getClientGrantTypes());
        clientDetails.setAccessTokenValidity(app.getAccessTokenValidSec());
        clientDetails.setRefreshTokenValidity(app.getRefreshTokenValidSec());
        clientDetailsService.save(clientDetails);


    }

}
