package com.he.model.entity.sys;

import com.alibaba.fastjson.annotation.JSONField;
import com.he.model.entity.SuperEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User extends SuperEntity<Long> {
	/**
	 * 用户姓名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 生日
	 */
	@JSONField(format = "yyyyMMdd")
	private Date birthday;
	/**
	 * 地址
	 */
	private String address;

	@Override
	public String toString() {
		return "User{" +"createTime"+getCreateTime()+
				"username='" + username + '\'' +
				", sex='" + sex + '\'' +
				", birthday=" + birthday +
				", address='" + address + '\'' +
				'}';
	}
}
