package com.youlai.common.web.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
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
 * 全局系统异常处理
 *
 * @author hxrui
 * @date 2020-02-25 13:54
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("非法参数异常，异常原因：{}",e.getMessage(),e);
        return Result.failed(e.getMessage());
    }


    @ExceptionHandler(JsonProcessingException.class)
    public Result handleJsonProcessingException(JsonProcessingException e) {
        log.error("Json转换异常，异常原因：{}",e.getMessage(),e);
        return Result.failed(e.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public Result handleBizException(BizException e) {
        log.error("业务异常，异常原因：{}",e.getMessage(),e);
        if (e.getResultCode() != null) {
            return Result.failed(e.getResultCode());
        }
        return Result.failed(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("未知异常，异常原因：{}",e.getMessage(),e);
        return Result.failed(e.getMessage());
    }
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public Result bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.error("参数异常，异常原因：{}"+fieldErrors.get(0).getDefaultMessage());
        return Result.failed(fieldErrors.get(0).getDefaultMessage());
    }
}
