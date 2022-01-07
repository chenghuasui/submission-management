package top.mingde.security.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 
 */
@ApiModel("运营会话用户")
public class OperationSessionUser extends User {

    /**
     *
     */
    private static final long serialVersionUID = -2248067611550728589L;

    @ApiModelProperty("用户ID")
    @Getter
    private final Long id;

    @ApiModelProperty("用户名称")
    @Getter
    private final String name;

    @ApiModelProperty("组织ID")
    @Getter
    private final Integer orgId;

    @ApiModelProperty("手机号")
    @Getter
    private final String phone;


    @ApiModelProperty("用户管理类型")
    @Getter
    private final Boolean superFlag;

    @ApiModelProperty("初始化")
    @Getter
    private final Boolean initFlag;

    public OperationSessionUser(
            String username,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities,

            /**
             * expand
             */
            Long id,
            String name,
            Integer orgId,
            String phone,
            Boolean superFlag,
            Boolean initFlag) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        /**
         * expand
         */
        this.id = id;
        this.name = name;
        this.orgId = orgId;
        this.phone = phone;
        this.superFlag = superFlag;
        this.initFlag = initFlag;
    }

}
