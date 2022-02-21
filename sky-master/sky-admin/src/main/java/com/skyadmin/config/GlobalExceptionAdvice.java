package com.skyadmin.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yue WeiWei
 * @date 2022/2/19 22:56
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Object HandlerDefaultException(Exception e) {
        e.printStackTrace();
        return "sys EER";
    }
}
