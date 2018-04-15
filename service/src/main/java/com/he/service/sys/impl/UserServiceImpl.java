package com.he.service.sys.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.he.dao.mapper.sys.UserMapper;
import com.he.model.entity.sys.User;
import com.he.service.sys.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {
    @Resource
    private UserMapper  userMapper;

    @Override
    public int addUser(User user) {
        userMapper.insert(user);
        return 0;
    }
}
