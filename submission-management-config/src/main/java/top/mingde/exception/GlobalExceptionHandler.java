package top.mingde.exception;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import top.mingde.tool.core.exception.category.JsonException;
import top.mingde.tool.core.result.Result;
import top.mingde.tool.core.result.ResultStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 统一异常控制器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<Void> globalExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error("from url  = " + request.getRequestURI() + " ,unkown error!", e);

        String message = StrUtil.isNotBlank(e.getMessage()) ? e.getMessage() : ResultStatus.FAIL.getMessage();

        return Result.fail(message);
    }

    /**
     * 文件过大
     *
     * @param request
     * @param e
     * @return
     * @author wangmin1994@qq.com
     * @since 2019-07-08 14:19:49
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<Void> maxUploadSizeExceededExceptionHandler(HttpServletRequest request, Exception e) {
        return Result.fail(ResultEnum.FILE_TOO_LARGE);
    }

    /**
     * 参数错误
     *
     * @param e
     * @return
     * @author wangmin1994@qq.com
     * @since 2019-08-09 11:48:34
     */
    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    public Result<Void> MissingServletRequestParameterExceptionhandler(HttpServletRequest request, Exception e) {
        log.warn("from url  = " + request.getRequestURI() + " ,请求参数错误", e);
        return Result.fail(ResultEnum.PARAM_FAIL);
    }

    /**
     * 统一 json 异常处理
     *
     * @param e JsonException
     * @return 统一返回 json 格式
     */
    @ExceptionHandler(value = JsonException.class)
    public Result<Void> jsonErrorHandler(HttpServletRequest request, JsonException e) {
        log.warn("from url  = " + request.getRequestURI() + " ,JsonException", e);

        String message = StrUtil.isNotBlank(e.getMessage()) ? e.getMessage() : ResultStatus.FAIL.getMessage();
        Integer code = Objects.isNull(e.getCode()) ? ResultStatus.FAIL.getCode() : e.getCode();

        return Result.fail(message, code);
    }



}
