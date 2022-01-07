package top.mingde.security.interceptor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.servlet.HandlerInterceptor;
import top.mingde.tool.core.instance.ConstVal;
import top.mingde.tool.core.interceptor.AuthContextHolder;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@SuppressWarnings("deprecation")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationHandlerInterceptor implements HandlerInterceptor {

    @NotNull
    private final TokenStore tokenStore;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String path = request.getServletPath();
        if(StrUtil.isNotBlank(token)){
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(token.replace(ConstVal.BEARER, ""));
            new JSONObject(accessToken.getAdditionalInformation());
            AuthContextHolder.getInstance().setContext(
                    ObjectUtil.defaultIfNull(new JSONObject(accessToken.getAdditionalInformation()), new JSONObject()));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        AuthContextHolder.getInstance().clear();
    }
}
