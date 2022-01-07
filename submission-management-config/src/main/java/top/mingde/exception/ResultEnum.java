package top.mingde.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.mingde.tool.core.exception.error.IErrorCode;
import top.mingde.tool.core.result.Result;

import java.text.MessageFormat;

/**
 * <p>
 * 返回枚举
 * </p>
 *
 * @author wangmin.cosmo@haier.com
 * @since 2021-07-22 11:27:05
 */
@Getter
@RequiredArgsConstructor
public enum ResultEnum implements IErrorCode{

    // 4____ 请求类型错误
    PARAM_FAIL(4001, "参数错误"),

    FILE_TOO_LARGE(4005, "文件过大"),

    // 5___ 服务器类型错误
    INTERNAL_ERROR(5000, "服务器内部错误"),

    SERVICE_UNAVAILABLE(5003, "服务不可用"),

    NETWORK_TIMEOUT(5004, "网络超时"),

    SERVER_BUSY(5005, "服务器繁忙，请稍后重试"),

    SERVER_ERROR(5006, "服务器异常"),


    ;

    /**
     * code编码
     */
    private final int code;
    /**
     * 中文信息描述
     */
    private final String message;

    public <T> Result<T> toResultFail() {
        return Result.fail(null, this.getMessage(), this.getCode());
    }

    public <T> Result<T> toResultFail(Object... args) {
        return Result.fail(null, MessageFormat.format(this.getMessage(), args), this.getCode());
    }

    public <T> Result<T> toResultFail(T data, Object... args) {
        return Result.fail(data, MessageFormat.format(this.getMessage(), args), this.getCode());
    }



}
