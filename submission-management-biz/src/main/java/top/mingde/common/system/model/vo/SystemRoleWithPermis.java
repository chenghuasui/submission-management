package top.mingde.common.system.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value="角色对象和权限ID集合", description="")
public class SystemRoleWithPermis  {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    /**
     * 权限ID集合
     */
    @ApiModelProperty(value = "权限ID集合")
    List<String> permiIds=new ArrayList<>();

}
