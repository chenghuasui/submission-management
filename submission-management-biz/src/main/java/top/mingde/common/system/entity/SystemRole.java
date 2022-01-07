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
 * 系统角色表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("system_role")
@ApiModel(value="SystemRole对象", description="系统角色表")
public class SystemRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id; 

    @ApiModelProperty(value = "角色名称")
    @TableField("NAME")
    @NotBlank(message="角色名称不能为空!")
    @Length(max=32,message="角色名称长度不大于32")
    private String name; 

    @ApiModelProperty(value = "角色编码")
    @TableField("CODE")
    @Length(max=32,message="角色编码长度不大于32")
    private String code; 

    @ApiModelProperty(value = "经营类型 ")
    @TableField("OPERATE_TYPE")
    @Length(max=1,message="经营类型 长度不大于1")
    private String operateType; 

    @ApiModelProperty(value = "是否系统内置")
    @TableField("DEFAULT_FLAG")
    @NotBlank(message="是否系统内置不能为空!")
    private String defaultFlag; 

    @ApiModelProperty(value = "角色等级 0高级 1一般")
    @TableField("LEVEL_FLAG")
    @NotBlank(message="角色等级 0高级 1一般不能为空!")
    private String levelFlag; 

    @ApiModelProperty(value = "角色描述")
    @TableField("NOTE")
    @Length(max=255,message="角色描述长度不大于255")
    private String note; 

    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Integer sort; 

    @ApiModelProperty(value = "拼音")
    @TableField("PINYIN")
    @Length(max=255,message="拼音长度不大于255")
    private String pinyin;

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
