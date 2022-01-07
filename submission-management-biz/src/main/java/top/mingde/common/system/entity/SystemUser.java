/*
*   
*/
package top.mingde.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("system_user")
@ApiModel(value="User对象", description="系统用户表")
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "登录名")
    @TableField("LOGIN_NAME")
    @NotBlank(message = "loginName不能为空!")
    @Length(max=32,message="登录名长度不大于32")
    private String loginName;

    @ApiModelProperty(value = "登录密码")
    @TableField("LOGIN_PWD")
    @NotBlank(message = "loginPwd不能为空!")
    @Length(max=255,message="登录密码长度不大于255")
    private String loginPwd;

    @ApiModelProperty(value = "明文密码")
    @TableField("USER_PWD")
    @Length(max=32,message="userPlainPwd长度不大于32")
    private String userPwd;

    @ApiModelProperty(value = "用户全称")
    @TableField("USER_NAME")
    @Length(max=32,message="userName长度不大于32")
    private String userName;

    @ApiModelProperty(value = "身份证号码")
    @TableField("USER_ID_CARD")
    @Length(max=20,message="userIdCard长度不大于20")
    private String userIdCard;

    /**
     * （[{"text":"NOT","value":"0","desc":"否"},{"text":"IS","value":"1","desc":"是"}]）
     */
    @ApiModelProperty(value = "初始化标识")
    @TableField("INIT_FLAG")
    @NotBlank(message = "initFlag不能为空!")
    private String initFlag;

    /**
     * （[{"text":"NOT","value":"0","desc":"否"},{"text":"IS","value":"1","desc":"是"}]）
     */
    @ApiModelProperty(value = "管理员级别")
    @TableField("ADMIN_STATUS")
    @NotBlank(message = "adminStatus不能为空!")
    private String adminStatus;

    @ApiModelProperty(value = "用户电话")
    @TableField("PHONE")
    @Length(max=16,message="phone长度不大于16")
    private String phone;

    @ApiModelProperty(value = "用户email")
    @TableField("EMAIL")
    @Length(max=255,message="email长度不大于255")
    private String email;

    @ApiModelProperty(value = "用户性别")
    @TableField("SEX")
    @Length(max=1,message="sex长度不大于1")
    private String sex;

    @ApiModelProperty(value = "用户头像")
    @TableField("AVATAR")
    @Length(max=255,message="avatar长度不大于255")
    private String avatar;

    @ApiModelProperty(value = "最后登录IP")
    @TableField("LOGIN_LAST_IP")
    @Length(max=16,message="loginLastIp长度不大于16")
    private String loginLastIp;

    @ApiModelProperty(value = "最后登录时间")
    @TableField("LOGIN_LAST_DATE")
    @Length(max=32,message="loginLastDate长度不大于32")
    private String loginLastDate;

    @ApiModelProperty(value = "用户描述")
    @TableField("NOTE")
    @Length(max=255,message="note长度不大于255")
    private String note;

    @ApiModelProperty(value = "版本号")
    @TableField(value = "VERSION", fill = FieldFill.INSERT)
    private Integer version;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_DATE", fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_DATE", fill = FieldFill.UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "数据标识")
    @TableField(value = "DEL_FLAG", fill = FieldFill.INSERT)
    private String delFlag;

    public String getDelFlagDesc(){
        if(StringUtils.isEmpty(delFlag)){
            return "未知的账号状态";
        }
        if(delFlag.equalsIgnoreCase("0")){
            return "正常";
        }else if(delFlag.equalsIgnoreCase("1")){
            return "删除";
        }else if(delFlag.equalsIgnoreCase("2")){
            return "禁用";
        }else{
            return "未知";
        }
    }



}
