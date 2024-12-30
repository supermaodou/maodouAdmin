package org.maodou.handler;

import org.maodou.domain.Result;
import org.maodou.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    // 处理自定义异常
    @ExceptionHandler(CustomException.class)
    public Result<?> handleCustomException(CustomException ex) {
        return Result.failure(ex.getCode(), ex.getMessage());
    }

    // 处理其他未捕获的异常
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception ex) {
        // 打印异常日志
        logger.info("系统内部错误: " + ex.getMessage());
        return Result.failure(500, "系统内部错误: " + ex.getMessage());
    }
}
