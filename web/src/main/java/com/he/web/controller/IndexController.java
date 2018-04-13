package com.he.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.he.model.entity.User;
import com.he.service.sys.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
public class IndexController {
    @Resource
    private IUserService    userService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        JSONObject j = new JSONObject();
        j.put("key","value");
        return j.toJSONString();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String test(){
        User u = new User();
        u.setUsername("hehe");
        userService.addUser(u);
        return "ok";
    }
}
