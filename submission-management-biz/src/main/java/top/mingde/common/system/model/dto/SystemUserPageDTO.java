/*
*   
*/
package top.mingde.common.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 
 * @since 2021-07-04 22:50:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UserDTO分页对象")
public class SystemUserPageDTO extends SystemUserDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页")
    @Min(value = 1, message = "当前页最小为1")
    @NotNull(message = "当前页不能为空!")
    private Integer pageNo;

    @ApiModelProperty(value = "每页显示条数")
    @NotNull(message = "每页显示条数不能为空!")
    private Integer pageSize;
}
