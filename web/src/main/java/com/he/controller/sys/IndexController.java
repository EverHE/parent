package com.he.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.he.controller.SuperController;
import com.he.entity.sys.SysUser;
import com.he.sys.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class IndexController extends SuperController {
    @Resource
    private IUserService userService;

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
        u.setAccount("tt");
        u.setPassword("1");
        u.setSalt("2");
        u.setName("hehe");
        //.addUser(u);
        userService.save(u);
        return "id:"+u.getId();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody SysUser u) {
        return userService.updateById(u)+"";
    }

    @RequestMapping("/del")
    @ResponseBody
    public Object del(Long id) {
        boolean  flag = userService.removeById(id);
        return flag;
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get() {
        SysUser user = userService.getOne(null);
        return user;
    }

    @RequestMapping("/test")
    @ResponseBody
    public Page<SysUser> test2(@RequestBody Page<SysUser> page) {
        userService.page(page);
        return page;
    }
    @RequestMapping("/date")
    @ResponseBody
    public String test2(Date date) {
        return date.toString();
    }
}
