package com.he.entity.sys;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.he.entity.BaseEntity;
import com.he.enums.SexEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_user")
public class SysUser extends BaseEntity<Long> {
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
	 * 密码盐
	 */
	@JSONField(serialize = false)
	private String salt;

	/**
	 * 性别，默认2-未知
	 */
	private SexEnum sex = SexEnum.SEX_UNKNOWN;
	/**
	 * 手机
	 */
	private Integer phone;
//	/**
//	 * 邮箱
//	 */
//	private String email;
//	/**
//	 * 修改密码时间
//	 */
//	private Date modPwdTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 区域id
	 */
	private String areaId;
}
