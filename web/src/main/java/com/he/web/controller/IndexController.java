package com.he.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.he.model.entity.User;
import com.he.service.sys.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController {
    @Resource
    IUserService    userService;

    @RequestMapping("/hello")
    public String hello(){
        JSONObject j = new JSONObject();
        j.put("key","value");
        return j.toJSONString();
    }

    @RequestMapping("/add")
    public String test(){
        User u = new User();
        u.setUsername("hehe");
        userService.addUser(u);
        return "ok";
    }
}
