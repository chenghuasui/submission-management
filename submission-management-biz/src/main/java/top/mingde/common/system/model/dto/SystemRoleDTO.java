package top.mingde.common.system.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Description
 * @Author sch
 * @Date 2021/4/19 18:07
 */
@Data
public class SystemRoleDTO {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message="角色名称不能为空!")
    private String name;

    @ApiModelProperty(value = "角色描述")
    @Length(max=255,message="角色描述长度不大于255")
    private String note;

}
