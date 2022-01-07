/*
*   
*/

package top.mingde.common.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.mingde.common.system.model.enums.AdminStatusEnum;
import top.mingde.common.system.model.enums.InitFlagEnum;
import top.mingde.model.enums.DelFlagEnum;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
* 
* @since 2021-07-04 22:50:48
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserDTO视图对象")
public class SystemUserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "登录密码")
    private String loginPwd;

    @ApiModelProperty(value = "明文密码")
    private String userPlainPwd;

    @ApiModelProperty(value = "用户全称")
    private String userName;

    @ApiModelProperty(value = "身份证号码")
    private String userIdCard;

    /**
     * （[{"text":"NOT","value":"0","desc":"否"},{"text":"IS","value":"1","desc":"是"}]）
     */
    @ApiModelProperty(value = "初始化标识")
    private String initFlag;

    /**
     * （[{"text":"NOT","value":"0","desc":"否"},{"text":"IS","value":"1","desc":"是"}]）
     */
    @ApiModelProperty(value = "管理员级别")
    private String adminStatus;

    @ApiModelProperty(value = "用户电话")
    private String phone;

    @ApiModelProperty(value = "用户email")
    private String email;

    @ApiModelProperty(value = "用户性别")
    private String sex;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "最后登录IP")
    private String loginLastIp;

    @ApiModelProperty(value = "最后登录时间")
    private String loginLastDate;

    @ApiModelProperty(value = "用户描述")
    private String note;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "数据标识")
    private String delFlag;

    public String getDelFlagDesc(){
        return DelFlagEnum.getDesc(this.getDelFlag());
    }

    @ApiModelProperty(value = "初始化标识枚举描述")
    public String getInitFlagDesc(){
        return InitFlagEnum.getDesc(this.getInitFlag());
    }

    @ApiModelProperty(value = "管理员级别枚举描述")
    public String getAdminStatusDesc(){
        return AdminStatusEnum.getDesc(this.getAdminStatus());
    }
}