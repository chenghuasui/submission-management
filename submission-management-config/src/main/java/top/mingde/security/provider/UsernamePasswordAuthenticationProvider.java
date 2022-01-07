package top.mingde.security.provider;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import top.mingde.tool.core.exception.category.JsonException;

import java.util.LinkedHashMap;

@Slf4j
public class UsernamePasswordAuthenticationProvider extends DaoAuthenticationProvider {

    @Getter
    @Setter
    private CacheManager cacheManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LinkedHashMap<String, Object> detailsMap = (LinkedHashMap<String, Object>) authentication.getDetails();
        return super.authenticate(authentication);
    }

    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String username = userDetails.getUsername();
        String presentedPassword = authentication.getCredentials().toString();
        if (!super.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
            throw new JsonException("用户名或者密码输入错误");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    protected void doAfterPropertiesSet() {
        super.doAfterPropertiesSet();
    }
}
