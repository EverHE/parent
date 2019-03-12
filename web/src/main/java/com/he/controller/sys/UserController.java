package com.he.controller.sys;

import com.he.controller.SuperController;
import com.he.sys.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController extends SuperController {
    @Resource
    IUserService userService;

    @RequestMapping("test")
    @ResponseBody
    public Object test(){
        return "test";
    }

    @RequestMapping("test2")
    @ResponseBody
    public Object test2(){
        userService.test();
        return "test";
    }

}
