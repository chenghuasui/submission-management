/*
*   
*/
package top.mingde.common.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 应用信息表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pay_app")
public class App implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 应用名称
     */
    @TableField("APP_NAME")
    @NotBlank(message = "appName不能为空!")
    @Length(max=64,message="应用名称长度不大于64")
    private String appName;

    /**
     * 应用编码
     */
    @TableField("APP_CODE")
    @NotBlank(message = "appCode不能为空!")
    @Length(max=64,message="应用编码长度不大于64")
    private String appCode;

    /**
     * 应用logo
     */
    @TableField("APP_LOGO")
    @Length(max=255,message="appLogo长度不大于255")
    private String appLogo;

    /**
     * 应用类型
     */
    @TableField("APP_TYPE")
    @Length(max=1,message="appType长度不大于1")
    private String appType;

    /**
     * 应用描述
     */
    @TableField("APP_NOTE")
    @Length(max=255,message="appNote长度不大于255")
    private String appNote;

    /**
     * 客户端ID
     */
    @TableField("CLIENT_ID")
    @NotBlank(message = "clientId不能为空!")
    @Length(max=256,message="客户端ID长度不大于256")
    private String clientId;

    /**
     * 客户端密匙
     */
    @TableField("CLIENT_SECRET")
    @Length(max=256,message="clientSecret长度不大于256")
    private String clientSecret;

    /**
     * 权限范围
     */
    @TableField("CLIENT_SCOPE")
    @Length(max=256,message="clientScope长度不大于256")
    private String clientScope;

    /**
     * 客户端支持的授权方式
     */
    @TableField("CLIENT_GRANT_TYPES")
    @Length(max=256,message="clientGrantTypes长度不大于256")
    private String clientGrantTypes;

    /**
     * 访问令牌有效时间值(单位:秒)
     */
    @TableField("ACCESS_TOKEN_VALID_SEC")
    private Integer accessTokenValidSec;

    /**
     * 更新令牌有效时间值(单位:秒)
     */
    @TableField("REFRESH_TOKEN_VALID_SEC")
    private Integer refreshTokenValidSec;

    /**
     * 创建人
     */
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_DATE", fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 更新人
     */
    @TableField(value = "UPDATE_BY", fill = FieldFill.UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_DATE", fill = FieldFill.UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    /**
     * 数据标识
     */
    @TableField(value = "DEL_FLAG", fill = FieldFill.INSERT)
    private String delFlag;


}
