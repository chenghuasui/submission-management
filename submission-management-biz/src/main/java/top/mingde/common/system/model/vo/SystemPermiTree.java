package top.mingde.common.system.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 输出系统权限和权限子集时候使用
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="权限对象和权限子集", description="系统权限表")
public class SystemPermiTree { //extends TreeNode {

    private String code;

    private String name;

}
