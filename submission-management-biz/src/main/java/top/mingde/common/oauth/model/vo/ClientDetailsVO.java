/*
*   
*/

package top.mingde.common.oauth.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ClientDetailsDTO视图对象")
public class ClientDetailsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户端ID")
    private String clientId;

    @ApiModelProperty(value = "资源ID集合,多个资源时用逗号(,)分隔")
    private String resourceIds;

    @ApiModelProperty(value = "客户端密匙")
    private String clientSecret;

    @ApiModelProperty(value = "客户端申请的权限范围")
    private String scope;

    @ApiModelProperty(value = "客户端支持的grant_type")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "重定向URI")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔")
    private String authorities;

    @ApiModelProperty(value = "访问令牌有效时间值(单位:秒)")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "更新令牌有效时间值(单位:秒)")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "预留字段")
    private String additionalInformation;

    @ApiModelProperty(value = "用户是否自动Approval操作")
    private String autoapprove;
}