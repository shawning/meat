package com.youlai.common.exception;

import com.youlai.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


/**
 * @author liusy
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    /**
     * validation Exception
     *
     * @param exception
     * @return R
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.warn(fieldErrors.get(0).getDefaultMessage());
        return Result.failed(fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * 身份验证异常信息.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(DeniedException.class)
    @ResponseStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
    public Result exception(DeniedException e) {
        log.error("身份验证异常信息 ex={}", e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    /**
     * 业务异常信息.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result exception(BizException e) {
        log.error("业务异常信息 ex={}", e.getMessage(), e);
        return Result.failed(e.getMessage());
    }
}
