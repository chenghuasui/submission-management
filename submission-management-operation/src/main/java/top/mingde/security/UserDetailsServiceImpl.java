package top.mingde.security;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import top.mingde.common.system.entity.SystemUser;
import top.mingde.common.system.model.enums.AdminStatusEnum;
import top.mingde.common.system.model.enums.InitFlagEnum;
import top.mingde.common.system.service.ISystemUserService;
import top.mingde.model.enums.DelFlagEnum;
import top.mingde.security.model.vo.OperationSessionUser;

import java.util.List;

@Component("UserDetailsService")
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final ISystemUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = userService.getOne(new LambdaQueryWrapper<SystemUser>()
                .eq(SystemUser::getLoginName, username));
        if(ObjectUtil.isNull(user)){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return this.create(user, Lists.newArrayList());
        }
    }

    /**
     * 构建权限用户
     * @param user
     * @param authorities
     * @return
     */
    private OperationSessionUser create(SystemUser user, List<GrantedAuthority> authorities) {
        return new OperationSessionUser(
                user.getLoginName(),
                user.getLoginPwd(),
                StrUtil.equals(DelFlagEnum.NORMAL.getValue(), user.getDelFlag()),
                true,
                true,
                true,
                authorities,
                user.getId(),
                user.getUserName(),
                null,
                user.getPhone(),
                StrUtil.equals(user.getAdminStatus(), AdminStatusEnum.IS.getValue()),
                StrUtil.equals(user.getInitFlag(), InitFlagEnum.IS.getValue())
        );
    }


}
