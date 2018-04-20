package com.he.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value=Exception.class)
    public ModelAndView allExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        String errorMessage = null;

        //自定义的已知错误直接返回错误信息
        if (e instanceof BaseException) {
            errorMessage = e.getMessage();
        } else if (e instanceof BaseRuntimeException) {
            errorMessage = e.getMessage();
        } else {
            //其他错误打印error日志
            errorMessage = "未知错误,请联系管理员!";
            log.error(e.getMessage(), e);
        }
        e.printStackTrace();

        Map<String, Object> result = new HashMap();
        result.put("error", true);
        result.put("message", errorMessage);

        return new ModelAndView("error/500", result);
    }
}
