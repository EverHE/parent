package com.he.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * 用户姓名
	 */
	private String username;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 地址
	 */
	private String address;

}
