package top.mingde.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import top.mingde.security.properities.PermitAllUrlProperties;
import top.mingde.tool.core.oauth2.AuthAccessDeniedHandler;
import top.mingde.tool.core.oauth2.AuthBearerTokenExtractor;
import top.mingde.tool.core.oauth2.AuthExceptionEntryPoint;

@SuppressWarnings("deprecation")
@Slf4j
@Configuration
@EnableResourceServer
@EnableConfigurationProperties({PermitAllUrlProperties.class})
@AllArgsConstructor
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final TokenStore tokenStore;
    private final PermitAllUrlProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>
            .ExpressionInterceptUrlRegistry config = http.requestMatchers().anyRequest()
            .and().authorizeRequests();
        properties.getUrls().forEach(e -> {
            config.antMatchers(e).permitAll();
        });
        config.anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resource) throws Exception {
        resource
            .tokenStore(tokenStore)
            .authenticationEntryPoint(new AuthExceptionEntryPoint())
            .tokenExtractor(new AuthBearerTokenExtractor(properties.getUrls()))
            .accessDeniedHandler(new AuthAccessDeniedHandler());
    }
}
