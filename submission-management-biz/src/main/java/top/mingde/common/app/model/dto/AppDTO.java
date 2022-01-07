/*
*   
*/
package top.mingde.common.app.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 应用logo
     */
    private String appLogo;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 应用描述
     */
    private String appNote;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端密匙
     */
    private String clientSecret;

    /**
     * 权限范围
     */
    private String clientScope;

    /**
     * 客户端支持的授权方式
     */
    private String clientGrantTypes;

    /**
     * 访问令牌有效时间值(单位:秒)
     */
    private Integer accessTokenValidSec;

    /**
     * 更新令牌有效时间值(单位:秒)
     */
    private Integer refreshTokenValidSec;

    /**
     * 数据标识
     */
    private String delFlag;
}
