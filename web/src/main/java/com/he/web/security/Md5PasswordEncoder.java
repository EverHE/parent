package com.he.web.security;

import cn.hutool.crypto.SecureUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码加密算法安全接口
 * Created by xuliangjun on 17/5/23.
 */
public class Md5PasswordEncoder implements PasswordEncoder {

    /**
     * 密码加密
     * @param pass 需要加密的字符串
     * @return
     */
    @Override
    public String encode(CharSequence pass) {
        return SecureUtil.md5(pass.toString());
    }

    /**
     * 密码校验
     * @param pass1 加密前的输入密码
     * @param pass2 加密后存储的密码
     * @return
     */
    @Override
    public boolean matches(CharSequence pass1, String pass2) {
        String _pass =  SecureUtil.md5(pass1.toString());
        return _pass.equals(pass2);
    }

    /**
     * 生成密码
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(SecureUtil.md5("123456"));
    }
}
