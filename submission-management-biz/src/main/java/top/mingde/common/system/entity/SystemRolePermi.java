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
 * 系统角色权限表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("system_role_permi")
@ApiModel(value="SystemRolePermi对象", description="系统角色权限表")
public class SystemRolePermi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableId("ROLE_ID")
    @NotNull(message = "权限ID不能为空!")
    private Integer roleId; 

    @ApiModelProperty(value = "权限ID")
    @TableField("PERMI_ID")
    @NotNull(message = "权限ID不能为空!")
    private Integer permiId;


}
