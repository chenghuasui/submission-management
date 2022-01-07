/*
*   
*/
package top.mingde.common.app.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AppPageDTO extends AppDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @Min(value = 1, message = "当前页最小为1")
    @NotNull(message = "当前页不能为空!")
    private Integer pageNo;

    /**
     * 每页显示条数
     */
    @NotNull(message = "每页显示条数不能为空!")
    private Integer pageSize;
}
