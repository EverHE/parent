package com.he.util;

public class JsonConstant {
	//
	public static final  String NOT_LEGAL_JSON="{\"err\":\"-9999\",\"result\":\"报文错误\"}";
	public static final  String TOKEN_ERR_JSON="{\"err\":\"-9801\",\"result\":\"您的账号已在其他设备登录\"}";
	public static final  String TOKEN_DEFIND_JSON="{\"err\":\"-9800\",\"result\":\"token不存在\"}";
	
	///登录模块返回Json定义
	public static final  String NOT_UNIQUE_MBLNO_JSON="{\"err\":\"-8000\",\"result\":\"手机号码不唯一\"}";
	public static final  String NOT_EXIST_MBLNO_JSON="{\"err\":\"-8001\",\"result\":\"手机号码不存在\"}";
	public static final  String FORMAT_ERR_MBLNO_JSON="{\"err\":\"-8002\",\"result\":\"手机号码格式错误\"}";
	public static final  String USER_PASSWD_ERR_JSON="{\"err\":\"-3\",\"result\":\"密码错误\"}";
	public static final  String USER_VALIDATECODE_DEFIND_JSON="{\"err\":\"-4\",\"result\":\"验证码不存在\"}";
	public static final  String USER_VALIDATECODE_ERR_JSON="{\"err\":\"-1\",\"result\":\"验证码错误\"}";
	public static final  String USER_LOGIN_ERR_JSON="{\"err\":\"$ERRCODE$\",\"result\":\"$MSG$\"}";
	public static final  String USER_CHANGE_PSW_JSON="{\"err\":\"$ERRCODE$\",\"result\":\"$MSG$\"}";
	public static final  String LOGIN_SUCCESS_JSON="{\"ret\":{\"err\":\"0\",\"result\":\"登录成功\",\"token\":\"$TOKEN$\"},\"grpinfo\":$GRPINFO$}";
	
	//修改密码
	public static final  String USER_OLDPASSWD_ERR_JSON="{\"err\":\"-2\",\"result\":\"原密码不正确\"}";
	public static final  String USER_CHANGEPASSWD_ERR_JSON="{\"err\":\"-1\",\"result\":\"密码修改失败\"}";
	public static final  String USER_CHANGEPASSWD_SUCCESS_JSON="{\"err\":\"0\",\"result\":\"密码修改成功\"}";
	
	//用户信息
	public static final  String NOT_EXIST_ACCOUNT_JSON="{\"err\":\"-9990\",\"result\":\"用户信息不存在\"}";
	public static final  String IS_EXIST_ACCOUNT_JSON="{\"err\":\"-9991\",\"result\":\"用户名已存在\"}";
	public static final  String CHANGE_USERINFO_ERR_JSON="{\"err\":\"-1\",\"result\":\"用户信息修改失败\"}";
	public static final  String CHANGE_USERINFO_SUCCESS_JSON="{\"err\":\"0\",\"result\":\"用户信息修改成功\"}";
	
	//用户注册
	public static final  String USER_REGISTER_SUCCESS_JSON="{\"err\":\"0\",\"result\":\"用户注册成功\"}";
	public static final  String USER_REGISTER_ERR_JSON="{\"err\":\"-1\",\"result\":\"用户注册失败\"}";
	
	//获取验证码
	public static final  String get_VALIDATECODE_ERR_JSON = "{\"err\":\"-1\",\"result\":\"验证码获取失败\"}";
	public static final  String get_VALIDATECODE_SUCCESS_JSON = "{\"err\":\"0\",\"result\":\"验证码获取成功\"}";
	
	//上传头像
	public static final  String ICON_UPLOAD_ERR_JSON = "{\"err\":\"-1\",\"result\":\"上传头像失败\"}";
	public static final  String ICON_UPLOAD_SUCCESS_JSON = "{\"err\":\"0\",\"result\":\"上传头像成功\"}";

	//获取企业列表
	public static final  String GET_LIST_ERR_JSON = "{\"err\":\"-1\",\"result\":\"获取列表失败\"}";

	//获取企业详情
	public static final  String GET_DETAIL_ERR_JSON = "{\"err\":\"-1\",\"result\":\"获取详情失败\"}";
	
	//企业类型
	public static final  String TARGET_TYPE_ERR_JSON="{\"err\":\"-9100\",\"result\":\"type：企业类型不正确\"}";
	
	public static final  String CURRENTPAGE_ERR_JSON="{\"err\":\"-9200\",\"result\":\"currentPage：当前页数错误\"}";
	
	public static final  String PARAMETER_ILLEGAL_JSON="{\"err\":\"-9000\",\"result\":\"参数不合法\"}";
	
	public static final  String COMMENT_SUCCESS_JSON="{\"err\":\"0\",\"result\":\"发表评论成功\"}";

	public static final  String FEEDBACK_SUCCESS_JSON="{\"err\":\"0\",\"result\":\"意见提交成功\"}";
	
	public static final  String THIRDLOGIN_ERR_JSON="{\"err\":\"-1\",\"result\":\"三方登录失败\"}";
	
	//支付7000
	public static final  String ORDER_DEFIND_ERR_JSON="{\"err\":\"-7999\",\"result\":\"订单不存在\"}";
	
	public static final  String ORDER_STATUS_ERR_JSON="{\"err\":\"-7998\",\"result\":\"订单非待支付状态\"}";
	
	public static final  String REFUND_SOURCE_ERR_JSON="{\"err\":\"-7997\",\"result\":\"退款渠道与支付渠道不一致\"}";
	
	public static final  String REFUND_STATUS_ERR_JSON="{\"err\":\"-7996\",\"result\":\"非已完成订单不能退款\"}";
	
	public static final  String REFUND_AMT_ERR_JSON="{\"err\":\"-7995\",\"result\":\"退款金额不正确\"}";
	
	public static final  String REFUND_PAY_ERR_JSON="{\"err\":\"-7994\",\"result\":\"$MSG$\"}";
	
	public static final  String REFUND_ERR_JSON="{\"err\":\"-7993\",\"result\":\"申请退款失败\"}";
	
	//支付宝7800
	public static final  String ALIPAY_ERR_JSON="{\"err\":\"-7800\",\"result\":\"申请支付宝支付失败\"}";
	
	//微信7700
	public static final  String WX_UNIFIEDORDER_ERR_JSON="{\"err\":\"7700\",\"result\":\"$MSG$\"}";
	
	public static final  String WX_SIGN_ERR_JSON="{\"err\":\"7701\",\"result\":\"签名验证失败\"}";
	
	public static final  String WX_ORDERRES_ERR_JSON="{\"err\":\"7702\",\"result\":\"$MSG$\"}";
	
	public static final  String WX_UNRESPONSIVE_JSON="{\"err\":\"7703\",\"result\":\"微信支付无响应\"}";
	
	public static final  String WXPAY_ERR_JSON="{\"err\":\"7704\",\"result\":\"申请微信支付失败\"}";
}
