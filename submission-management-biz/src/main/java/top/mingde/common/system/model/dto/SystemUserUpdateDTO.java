package top.mingde.common.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sch
 */
@Data
@ApiModel("用户更新实体类")
public class SystemUserUpdateDTO {

    @ApiModelProperty(value = "主键ID")
    @NotNull(message = "主键ID不能为空")
    private Integer id;

    @ApiModelProperty("用户电话")
    private String phone;

    @NotBlank(message = "用户密码不能为空")
    @Length(max = 32, message = "用户密码长度不大于32")
    @ApiModelProperty("用户登录密码")
    private String userPwd;

    @NotEmpty(message = "用户权限id")
    @ApiModelProperty("用户权限id")
    List<Integer> roleId = new ArrayList<>();

}
