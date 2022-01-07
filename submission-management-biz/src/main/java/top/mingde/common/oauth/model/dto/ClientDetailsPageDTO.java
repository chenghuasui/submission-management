/*
*   
*/
package top.mingde.common.oauth.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="ClientDetailsDTO分页对象")
public class ClientDetailsPageDTO extends ClientDetailsDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页")
    @Min(value = 1, message = "当前页最小为1")
    @NotNull(message = "当前页不能为空!")
    private Integer pageNo;

    @ApiModelProperty(value = "每页显示条数")
    @NotNull(message = "每页显示条数不能为空!")
    private Integer pageSize;
}
