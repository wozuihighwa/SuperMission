package com.wozuihighwa.supmission.service;

import android.content.SharedPreferences;

/**
 * 设置业务逻辑
 * 
 * @author wozuihighwa
 * @date 2015-1-24
 */
public interface SettingService {
	/**
	 * 绑定账号
	 * 
	 * @param imei
	 * @param os_version
	 * @param device_name
	 * @param device_brand
	 * @param appversion
	 * @param deviceID
	 * @param phoneNumber
	 * @param realName
	 * @param sex
	 * @param age
	 * @param email
	 * @param successCallBack
	 * @param failCallBack
	 */
	public void bindAccount(String imei, String os_version, String device_name,
			String device_brand, String appversion, String deviceID,
			String phoneNumber, SharedPreferences sharedPrefs,
			SuccessCallBack successCallBack, FailCallBack failCallBack);

	/**
	 * 获取个人信息
	 * 
	 * @param imei
	 * @param os_version
	 * @param device_name
	 * @param device_brand
	 * @param appversion
	 * @param deviceID
	 * @param phoneNumber
	 * @param successCallBack
	 * @param failCallBack
	 */
	public void getUserInfo(String imei, String os_version, String device_name,
			String device_brand, String appversion, String deviceID,
			String phoneNumber, SuccessCallBack successCallBack,
			FailCallBack failCallBack);

	/**
	 * 设置个人信息
	 * 
	 * @param imei
	 * @param os_version
	 * @param device_name
	 * @param device_brand
	 * @param appversion
	 * @param deviceID
	 * @param phoneNumber
	 * @param realName
	 * @param sex
	 * @param age
	 * @param email
	 * @param successCallBack
	 * @param failCallBack
	 */
	public void setUserInfo(String imei, String os_version, String device_name,
			String device_brand, String appversion, String deviceID,
			String phoneNumber, String realName, String sex, String age,
			String email, SharedPreferences sharedPrefs,
			SuccessCallBack successCallBack, FailCallBack failCallBack);

	/**
	 * 设置支付宝账户
	 * 
	 * @param imei
	 * @param os_version
	 * @param device_name
	 * @param device_brand
	 * @param appversion
	 * @param deviceID
	 * @param phoneNumber
	 * @param alipayAccount
	 * @param accountName
	 * @param realName
	 * @param sex
	 * @param age
	 * @param email
	 * @param successCallBack
	 * @param failCallBack
	 */
	public void setAlipayInfo(String imei, String os_version,
			String device_name, String device_brand, String appversion,
			String deviceID, String phoneNumber, String alipayAccount,
			String alipayName, String realName, String sex, String age,
			String email, SharedPreferences sharedPrefs,
			SuccessCallBack successCallBack, FailCallBack failCallBack);

	/**
	 * 设置QQ号码
	 * 
	 * @param imei
	 * @param os_version
	 * @param device_name
	 * @param device_brand
	 * @param appversion
	 * @param deviceID
	 * @param phoneNumber
	 * @param qq
	 * @param realname
	 * @param sex
	 * @param age
	 * @param email
	 * @param successCallBack
	 * @param failCallBack
	 */
	public void setQQInfo(String imei, String os_version, String device_name,
			String device_brand, String appversion, String deviceID,
			String phoneNumber, String qq, String realname, String sex,
			String age, String email, SharedPreferences sharedPrefs,
			SuccessCallBack successCallBack, FailCallBack failCallBack);

	/**
	 * 请求成功后的回调接口
	 */
	public static interface SuccessCallBack {
		void onSuccess(String info);
	}

	/**
	 * 请求失败的回调接口
	 */
	public static interface FailCallBack {
		void onFail(String failInfo);
	}

}
