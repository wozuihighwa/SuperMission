package com.wozuihighwa.supmission.utils;

/**
 * 配置类
 * 
 * @author wozuihighwa
 * @date 2015-1-22
 */
public class Config {

	// 有米appID,appSecret
	public static final String YOUMI_APPID = "56410a520b78f7c6";
	public static final String YOUMI_APPSECRET = "52189621642341b3";
	
	// 点乐App_id
	public static final String DIANJOY_APPID = "dd33101d8a28011ec6ea169faa9233e4";
	
	// 万普appID,appPID
	public static final String WAPS_APPID = "6c0ca7af5b634a4e60c711b8dd122789";
	public static final String WAPS_APPPID = "defalut";
	
	// 上传URL
	public static final String URL_INDEX = "http://182.92.171.3:8088/Home/Index/index";// 设置设备信息
	public static final String URL_SET_USER_INFO = "http://182.92.171.3:8088/Home/Setting/setUserInfo";// 设置用户信息
	public static final String URL_SET_ALIPAY_ACCOUNT = "http://182.92.171.3:8088/Home/Setting/setAlipayInfo";// 设置支付宝信息
	public static final String URL_SET_QQ_NUMBER = "http://182.92.171.3:8088/Home/Setting/setQQInfo";// 设置QQ号码
	public static final String URL_BIND_ACCOUNT = "http://182.92.171.3:8088/Home/Setting/bindAccount";// 绑定账号
	public static final String URL_GET_USER_INFO = "http://182.92.171.3:8088/Home/Setting/getUserInfo";// 获取用户信息
	public static final String URL_WHETHER_CHARGED = "http://182.92.171.3:8088/Home/Account/whetherCharged";// 判断是否是首次提现,本周是否是第一次提现,验证设备是否被禁用
	public static final String URL_GET_ACCOUNT_INFO = "http://182.92.171.3:8088/Home/Account/getAccountInfo";// 获取用户账户信息
	public static final String URL_CHARGE_MOBILE = "http://182.92.171.3:8088/Home/Account/chargeMobile";// 手机充值
	public static final String URL_CHARGE_ALIPAY = "http://182.92.171.3:8088/Home/Account/chargeAlipay";// 支付宝充值
	public static final String URL_CHARGEQBEE = "http://182.92.171.3:8088/Home/Account/chargeQBee";// QQ充值

	// 判断
	public static final String STATUS_SUCCESS = "0";// 上传设备信息成功状态码
	public static final String STATUS_FAIL = "1";// 上传设备信息失败状态码
	public static final String ALREADY_BIND = "手机已绑定过应用！";// 之前已绑定手机
	public static final String NOT_ALREADLY_BIND = "欢迎新用户！";// 未绑定过手机
	// HTTP 请求方法
	public static final String HTTP_GET = "GET";// GET
	public static final String HTTP_POST = "POST";// POST
	// 规定字符编码
	public static final String CHARSET_UTF_8 = "UTF-8";// UTF-8
	public static final String VERIFY_PARAM = "wozuihighwa@caozhen";

	// 上传参数-设备信息
	public static final String IMEI = "imei";
	public static final String OS_VERSION = "os_version";
	public static final String DEVICE_NAME = "device_name";
	public static final String DEVICE_BRAND = "device_brand";
	public static final String APP_VERSION = "appversion";
	public static final String VERIFY = "verify";

	// 上传参数-个人信息
	public static final String REAL_NAME = "realName";
	public static final String SEX = "sex";
	public static final String AGE = "age";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String EMAIL = "email";

	// 上传参数-支付宝账户
	public static final String ALIPAY_ACCOUNT = "alipayAccount";
	public static final String ALIPAY_NAME = "alipayName";

	// 上传参数-QQ号码
	public static final String QQ_NUM = "qq";

	// 上传参数-deviceID
	public static final String DEVICE_ID = "deviceID";

}