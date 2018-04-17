package com.he.web.security;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.he.model.entity.sys.SysUser;
import com.he.service.sys.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 密码验证服务，用于spring security验证使用
 */
@Service
public class UserSecurityService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserSecurityService.class);

	@Autowired
	private IUserService userService;

	@Override
	public UserDetails loadUserByUsername(String account)
			throws UsernameNotFoundException {
		logger.debug("登录账号：", account);

		SysUser u = new SysUser();
		u.setAccount(account.toLowerCase());
		SysUser user = userService.selectOne(new EntityWrapper(u));
		if (user == null) {
			throw new UsernameNotFoundException("账户 " + account + " 不存在");
		}
		//TODO 添加角色信息
		//TODO 添加权限信息
		return new AuthUserDetails(user,null, null);
	}
}
