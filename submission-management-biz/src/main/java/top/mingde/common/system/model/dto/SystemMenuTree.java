package top.mingde.common.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import top.mingde.common.system.entity.SystemMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 输出系统菜单和菜单子集时候使用
 */
@ApiModel(value="SystemMenu对象和菜单子集", description="系统菜单表")
public class SystemMenuTree extends SystemMenu {

    /**
     * 子集
     */
    @ApiModelProperty(value = "菜单子集")
    @Getter
    @Setter
    List<SystemMenuTree> children = new ArrayList<>();

}
