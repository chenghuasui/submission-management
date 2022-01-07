package top.mingde.common.system.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import top.mingde.common.system.entity.SystemUser;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value="SystemUser对象和角色ID", description="")
public class SystemUserWithRole extends SystemUser {

    /**
     * 用户角色ID
     */
    @ApiModelProperty(value = "用户角色ID")
    @Getter
    @Setter
    @NotNull
    List<Integer> roleId=new ArrayList<>();

}
