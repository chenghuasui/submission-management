package top.mingde.common.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("用户新增实体类")
public class SystemUserSaveDTO {

    @NotBlank(message = "用户名不能为空")
    @Length(max=32, message="登录名长度不大于32")
    @ApiModelProperty("用户登录名")
    private String loginName;

    @ApiModelProperty("用户电话")
    private String phone;

    @NotBlank(message = "用户密码不能为空")
    @Length(max=32,message="用户密码长度不大于32")
    @ApiModelProperty("用户登录密码")
    private String userPwd;

    @NotEmpty(message = "用户权限id")
    @ApiModelProperty("用户权限id")
    List<Integer> roleId = new ArrayList<>();

}
