package top.mingde.common.system.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author sch
 * @Date 2021/4/19 18:07
 */
@Data
public class SystemRolePageDTO {

    @ApiModelProperty("第几页")
    private Integer pageNo;

    @ApiModelProperty("每页数量")
    private Integer pageSize;

    @ApiModelProperty(value = "角色名称")
    private String name;

}
