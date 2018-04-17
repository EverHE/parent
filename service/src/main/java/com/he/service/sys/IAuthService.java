package com.he.service.sys;

import com.he.model.entity.sys.SysUser;

public interface IAuthService {
    /**
     * 注册
     * @param user
     * @return
     */
    SysUser register(SysUser user);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 刷新token
     * @param oldToken
     * @return
     */
    String refresh(String oldToken);
}