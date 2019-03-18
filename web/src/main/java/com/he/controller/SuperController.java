package com.he.controller;

import cn.hutool.core.date.DatePattern;
import com.he.syslog.AppVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class SuperController {
    protected Logger logger = LoggerFactory.getLogger(SuperController.class);

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal();


    public HttpServletRequest getHttpRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 解决前台日期字符串和后台controller接收JavaBean的Date类型不一致的问题
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = threadLocal.get();
        if(dateFormat ==null){
            dateFormat= new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
            //严格解析
            dateFormat.setLenient(false);
        }

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 获取登录用户信息
     * @return AuthUserDetails
     */
    public AppVisitor getAuthUser(){
        return null;
        //return SecurityUtils.getSubject() != null && SecurityUtils.getSubject().getPrincipal() != null ? (AuthUserDetails)SecurityUtils.getSubject().getPrincipal() : null;
    }

    public AppVisitor getAuthUser(String sessionId){
        return null;
    }
}
