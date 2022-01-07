package top.mingde.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("枚举返回值实体视图对象")
public class EnumsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("枚举对象值")
    private String value;

    @ApiModelProperty("枚举对象描述")
    private String desc;

    @ApiModelProperty("枚举对象文本")
    private String text;

}

