/*
*   
*/

package top.mingde.common.app.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.mingde.model.enums.DelFlagEnum;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class AppVO implements Serializable {

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
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate;

    /**
     * 数据标识
     */
    private String delFlag;

    public String getDelFlagDesc(){
        return DelFlagEnum.getDesc(this.getDelFlag());
    }
}