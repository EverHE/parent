package com.he.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
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
    public ModelAndView allExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        String errorMessage = null;

        //自定义的已知错误直接返回错误信息
        if (e instanceof BaseException) {
            errorMessage = e.getMessage();
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            errorMessage = "参数转换失败";
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

        return new ModelAndView(new FastJsonJsonView(), result);
        //return new ModelAndView("error/500", result);
    }

//    /**
//     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
//     * @param binder
//     */
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {}
//
//    /**
//     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
//     * @param model
//     */
//    @ModelAttribute
//    public void addAttributes(Model model) {
//        //model.addAttribute("author", "Magical Sam");
//    }
}
