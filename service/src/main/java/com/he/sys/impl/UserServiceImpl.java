package com.he.sys.impl;

import com.he.base.impl.SuperServiceImpl;
import com.he.mapper.sys.UserMapper;
import com.he.entity.sys.SysUser;
import com.he.sys.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper,SysUser> implements IUserService, UserDetailsService {

    @Override
    public int addUser(SysUser user) {
        baseMapper.insert(user);
        return 0;
    }

    @Override
    public int test() {
        baseMapper.selectListBySQL();
        return 0;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
