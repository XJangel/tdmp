package com.jx.tdmp.common.exception;

import com.jx.tdmp.common.lang.ResponseResult;
import com.jx.tdmp.constant.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // TODO:根据不同的异常结果在ResultCode中制定相应的类型
    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public ResponseResult handle401(ShiroException e) {

        return new ResponseResult(ResultCode.fail);
    }
    /**
     * 处理Assert的异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public ResponseResult handler(IllegalArgumentException e) throws IOException {
        log.error("Assert异常:-------------->{}",e.getMessage());
        return new ResponseResult(ResultCode.fail);
    }
    /**
     * @Validated 校验错误异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult handler(MethodArgumentNotValidException e) throws IOException {
        log.error("运行时异常:-------------->",e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return new ResponseResult(ResultCode.fail);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseResult handler(RuntimeException e) throws IOException {
        log.error("运行时异常:-------------->",e);
        return new ResponseResult(ResultCode.fail);
    }
}
