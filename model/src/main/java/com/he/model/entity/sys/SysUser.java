package com.he.model.entity.sys;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.he.model.entity.SuperEntity;
import com.he.model.enums.SexEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName(value = "sys_user")
public class SysUser extends SuperEntity<Long> {
	/**
	 * 用户账号
	 */
	private String account;
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 密码
	 */
	//TODO ?? 无效
	@JSONField(serialize = false)
	private String password;
	/**
	 * 性别
	 */
	private SexEnum sex = SexEnum.SEX_UNKNOWN;
	/**
	 * 手机
	 */
	private Integer mobile;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 修改密码时间
	 */
	private Date modPwdTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 区域编码
	 */
	private String code;
}
