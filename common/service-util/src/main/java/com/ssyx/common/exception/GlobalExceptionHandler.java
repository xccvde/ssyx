package com.ssyx.common.exception;

import com.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SsyxException.class)
    @ResponseBody
    public Result<Object> error(SsyxException e) {
        e.printStackTrace();
        return Result.build(null, e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> error(Exception e) {
        e.printStackTrace();
        return Result.fail(null);
    }

}
