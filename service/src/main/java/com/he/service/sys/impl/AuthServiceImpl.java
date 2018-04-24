package com.he.service.sys.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.he.common.util.JwtTokenUtil;
import com.he.model.entity.sys.SysUser;
import com.he.model.enums.StatusEnum;
import com.he.service.sys.IAuthService;
import com.he.service.sys.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthServiceImpl implements IAuthService {
    @Resource
    private IUserService    userService;
    @Resource
    private AuthenticationManager   authenticationManager;
    @Resource
    private UserDetailsService  userDetailsService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public SysUser register(SysUser user) {
        //final修饰后只读
        final String account = user.getAccount();
        SysUser u = new SysUser();
        u.setAccount(account);
        u.setStatus(StatusEnum.STATUS_NORMAL);
        if(userService.selectOne(new EntityWrapper(u))!=null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        user.setModPwdTime(DateUtil.date());
        userService.insert(user);
        return user;
    }

    @Override
    public String login(String account, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(account, password);
//        //TODO ????
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(account);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getAccountFromToken(token);
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.validateToken(token, user)){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
