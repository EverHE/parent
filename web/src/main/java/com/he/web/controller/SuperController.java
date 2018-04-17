package com.he.web.controller;

import com.he.model.entity.sys.SysUser;
import com.he.web.security.AuthUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

//@Controller
public abstract class SuperController {
    private Logger logger = LoggerFactory.getLogger(SuperController.class);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 解决前台日期字符串和后台controller接收JavaBean的Date类型不一致的问题
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //严格解析
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 通过Spring Security 获取用户信息
     * @return
     */
    public SysUser getUserInfo(){
        SysUser user=null;
        try {
            Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (userDetails!=null){
                if (userDetails instanceof AuthUserDetails) {
                    user=((AuthUserDetails)userDetails).getUser();
                }
            }
        } catch (Exception e) {
            logger.debug("获取用户信息失败");
            e.printStackTrace();
            return null;
        }
        return user;
    }
}
