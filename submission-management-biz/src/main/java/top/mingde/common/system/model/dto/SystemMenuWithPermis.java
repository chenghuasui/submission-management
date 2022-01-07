package top.mingde.common.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import top.mingde.common.system.entity.SystemMenu;
import top.mingde.common.system.entity.SystemPermi;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入菜单对象和权限列表
 */
@ApiModel(value="菜单对象和权限列表", description="菜单对象和权限列表")
public class SystemMenuWithPermis extends SystemMenu {

    /**
     * 权限参数
     */
    @ApiModelProperty(value = "权限参数列表")
    @Getter
    @Setter
    List<SystemPermi> permissionList = new ArrayList<>();
}
