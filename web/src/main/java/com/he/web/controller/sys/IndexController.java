package com.he.web.controller.sys;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.he.model.entity.sys.SysUser;
import com.he.model.enums.StatusEnum;
import com.he.service.sys.IUserService;
import com.he.web.controller.SuperController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class IndexController extends SuperController {
    @Resource
    private IUserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        JSONObject j = new JSONObject();
        j.put("key", "value11");
        return j.toJSONString();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String test() {
        SysUser u = new SysUser();
        u.setStatus(StatusEnum.STATUS_NORMAL);
        u.setName("hehe");
        //.addUser(u);
        userService.insert(u);
        return "id:"+u.getId();
    }

    @RequestMapping("/del")
    @ResponseBody
    public Object del(Long id) {
        boolean  flag = userService.deleteById(id);
        return flag;
    }

    @RequestMapping("/delById")
    @ResponseBody
    public Object delById(Long id) {
        int  flag = userService.relDelById(id);
        return flag;
    }

    @RequestMapping("/delAll")
    @ResponseBody
    public Object delAll() {
        int  flag = userService.relDelAll();
        return flag;
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get() {
        SysUser user = userService.selectById(1);
        return user;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test2(Date date) {
        return DateUtil.formatDate(date);
    }
}
