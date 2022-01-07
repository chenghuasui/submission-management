/*
 * Copyright (c) 2010-2020, SIUFUNG
 */
package top.mingde.common.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 系统用户角色表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("system_user_role")
@ApiModel(value="SystemUserRole对象", description="系统用户角色表")
public class SystemUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "角色ID")
    @TableField("ROLE_ID")
    @NotNull(message = "角色ID不能为空!")
    private Integer roleId;


}
