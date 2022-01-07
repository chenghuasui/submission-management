/*
 * Copyright (c) 2010-2020, SIUFUNG
 */
package top.mingde.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统权限表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("system_permi")
@ApiModel(value="SystemPermi对象", description="系统权限表")
public class SystemPermi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id; 

    @ApiModelProperty(value = "权限名称")
    @TableField("NAME")
    @NotBlank(message="权限名称不能为空!")
    @Length(max=32,message="权限名称长度不大于32")
    private String name;

    @ApiModelProperty(value = "权限编码")
    @TableField("CODE")
    @Length(max=32,message="权限编码长度不大于32")
    private String code;

    @ApiModelProperty(value = "父级外键")
    @TableField("PARENT_ID")
    private Integer parentId; 

    @ApiModelProperty(value = "菜单外键")
    @TableField("MENU_ID")
    private Integer menuId; 

    @ApiModelProperty(value = "权限事件")
    @TableField("EVENT")
    @Length(max=255,message="权限事件长度不大于255")
    private String event; 

    @ApiModelProperty(value = "显示文本")
    @TableField("TEXT")
    @NotBlank(message="显示文本不能为空!")
    @Length(max=32,message="显示文本长度不大于32")
    private String text; 

    @ApiModelProperty(value = "权限类型")
    @TableField("TYPE")
    @Length(max=50,message="权限类型长度不大于50")
    private String type; 

    @ApiModelProperty(value = "权限URL")
    @TableField("URL")
    @Length(max=255,message="权限URL长度不大于255")
    private String url; 

    @ApiModelProperty(value = "权限方法")
    @TableField("METHOD")
    @Length(max=50,message="权限方法长度不大于50")
    private String method; 

    @ApiModelProperty(value = "权限标识")
    @TableField("PERMISSION")
    @NotBlank(message="权限标识不能为空!")
    @Length(max=255,message="权限标识长度不大于255")
    private String permission;

    @ApiModelProperty(value = "权限排序")
    @TableField("SORT")
    @NotNull(message = "权限排序不能为空!")
    private Integer sort;


    @ApiModelProperty(value = "是否校验")
    @TableField("CHECK_IGNORE")
    private String checkIgnore;

    @ApiModelProperty(value = "权限描述")
    @TableField("NOTE")
    @Length(max=255,message="权限描述长度不大于255")
    private String note;

    @ApiModelProperty(value = "拼音")
    @TableField("PINYIN")
    @Length(max=255,message="拼音长度不大于255")
    private String pinyin;

    @ApiModelProperty(value = "业务端")
    @TableField("OPERATIONS")
    @NotBlank
    @Length(max=255,message="业务端不大于16")
    private String operations;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy; 

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_DATE", fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate; 

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.UPDATE)
    @Length(max=32,message="更新人长度不大于32")
    private String updateBy; 

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_DATE", fill = FieldFill.UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateDate; 

    @ApiModelProperty(value = "状态标识 ( 0 正常 1删除 2禁用)")
    @TableField(value = "DEL_FLAG", fill = FieldFill.INSERT)
    private String delFlag; 


}
