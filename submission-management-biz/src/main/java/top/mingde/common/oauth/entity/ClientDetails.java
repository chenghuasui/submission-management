/*
*   
*/
package top.mingde.common.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 客户端信息
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oauth_client_details")
@ApiModel(value="ClientDetails对象", description="客户端信息")
public class ClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户端ID")
    @TableId("client_id")
    @NotBlank(message = "clientId不能为空!")
    @Length(max=256,message="客户端ID长度不大于256")
    private String clientId;

    @ApiModelProperty(value = "资源ID集合,多个资源时用逗号(,)分隔")
    @TableField("resource_ids")
    @Length(max=256,message="resourceIds长度不大于256")
    private String resourceIds;

    @ApiModelProperty(value = "客户端密匙")
    @TableField("client_secret")
    @Length(max=256,message="clientSecret长度不大于256")
    private String clientSecret;

    @ApiModelProperty(value = "客户端申请的权限范围")
    @TableField("scope")
    @Length(max=256,message="scope长度不大于256")
    private String scope;

    @ApiModelProperty(value = "客户端支持的grant_type")
    @TableField("authorized_grant_types")
    @Length(max=256,message="authorizedGrantTypes长度不大于256")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "重定向URI")
    @TableField("web_server_redirect_uri")
    @Length(max=256,message="webServerRedirectUri长度不大于256")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔")
    @TableField("authorities")
    @Length(max=256,message="authorities长度不大于256")
    private String authorities;

    @ApiModelProperty(value = "访问令牌有效时间值(单位:秒)")
    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "更新令牌有效时间值(单位:秒)")
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "预留字段")
    @TableField("additional_information")
    @Length(max=4096,message="additionalInformation长度不大于4,096")
    private String additionalInformation;

    @ApiModelProperty(value = "用户是否自动Approval操作")
    @TableField("autoapprove")
    @Length(max=256,message="autoapprove长度不大于256")
    private String autoapprove;


}
