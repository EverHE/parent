package com.he.web.controller.sys;

import com.he.web.controller.SuperController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController extends SuperController {

    @RequestMapping("test")
    @ResponseBody
    public Object test(){
        return "test";
    }

}
