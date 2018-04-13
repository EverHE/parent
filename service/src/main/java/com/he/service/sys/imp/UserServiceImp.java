package com.he.service.sys.imp;

import com.he.dao.mapper.sys.UserMapper;
import com.he.model.entity.User;
import com.he.service.sys.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImp implements IUserService {
    @Resource
    UserMapper  userMapper;

    @Override
    public int addUser(User user) {
        userMapper.insert(user);
        return 0;
    }
}
