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
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * 
 * @since 2020-02-12 17:29:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("system_menu")
@ApiModel(value="SystemMenu对象", description="系统菜单表")
public class SystemMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id; 

    @ApiModelProperty(value = "菜单名称")
    @TableField("NAME")
    @NotBlank(message="菜单名称不能为空!")
    @Length(max=32,message="菜单名称长度不大于32")
    private String name;

    @ApiModelProperty(value = "菜单编码")
    @TableField("CODE")
    @Length(max=32,message="菜单编码长度不大于32")
    private String code;

    @ApiModelProperty(value = "菜单父级ID")
    @TableField("PARENT_ID")
    private Integer parentId; 

    @ApiModelProperty(value = "菜单父级名称")
    @TableField("PARENT_NAME")
    @Length(max=32,message="菜单父级名称长度不大于32")
    private String parentName; 

    @ApiModelProperty(value = "菜单链接")
    @TableField("PATH")
    @Length(max=255,message="菜单链接长度不大于255")
    private String path; 

    @ApiModelProperty(value = "执行文件")
    @TableField("COMPONENT")
    @Length(max=255,message="执行文件长度不大于255")
    private String component; 

    @ApiModelProperty(value = "页面缓存")
    @TableField("KEEP_ALIVE")
    @Length(max=1,message="页面缓存长度不大于1")
    private String keepAlive; 

    @ApiModelProperty(value = "菜单图标")
    @TableField("ICON")
    @Length(max=255,message="菜单图标长度不大于255")
    private String icon; 

    @ApiModelProperty(value = "菜单级别")
    @TableField("LEVEL")
    private Integer level; 

    @ApiModelProperty(value = "菜单事件")
    @TableField("EVENT")
    @Length(max=255,message="菜单事件长度不大于255")
    private String event; 

    @ApiModelProperty(value = "菜单类型 0不可访问 1可访问")
    @TableField("REQUESTABLE")
    @NotBlank(message="菜单类型不能为空!")
    private String requestable;

    @ApiModelProperty(value = "菜单标识")
    @TableField("SIGN")
    @Length(max=50,message="菜单标识长度不大于50")
    private String sign;

    @ApiModelProperty(value = "菜单描述")
    @TableField("NOTE")
    @Length(max=255,message="菜单描述长度不大于255")
    private String note; 

    @ApiModelProperty(value = "菜单排序")
    @TableField("SORT")
    private Integer sort; 

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
