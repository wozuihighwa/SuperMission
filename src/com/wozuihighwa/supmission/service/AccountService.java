package com.wozuihighwa.supmission.service;

import android.content.SharedPreferences;

/**
 * 用户账户业务逻辑
 * 
 * @author wozuihighwa
 * @date 2015-1-26
 */
public interface AccountService {

	/**
	 * 判断是否是首次提现,本周是否是第一次提现,验证设备是否被禁用
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
	public void whetherCharged(String imei, String os_version,
			String device_name, String device_brand, String appversion,
			String deviceID, String phoneNumber, SharedPreferences sharedPrefs,
			SuccessCallBack successCallBack, FailCallBack failCallBack);

	/**
	 * 获取用户账户信息
	 * 
	 * @param imei
	 * @param os_version
	 * @param device_name
	 * @param device_brand
	 * @param appversion
	 * @param deviceID
	 * @param successCallBack
	 * @param failCallBack
	 */
	public void getAccountInfo(String imei, String os_version,
			String device_name, String device_brand, String appversion,
			String deviceID, SharedPreferences sharedPrefs,
			SuccessCallBack successCallBack, FailCallBack failCallBack);

	/**
	 * 话费提现
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
	 * @param sharedPrefs
	 * @param successCallBack
	 * @param failCallBack
	 */
	public void chargeMobile(String imei, String os_version,
			String device_name, String device_brand, String appversion,
			String deviceID, String phoneNumber, String realName, String sex,
			String age, String email, SuccessCallBack successCallBack,
			FailCallBack failCallBack);

	/**
	 * 支付宝提现
	 * 
	 * @param imei
	 * @param os_version
	 * @param device_name
	 * @param device_brand
	 * @param appversion
	 * @param deviceID
	 * @param phoneNumber
	 * @param alipayAccount
	 * @param alipayName
	 * @param successCallBack
	 * @param failCallBack
	 */
	public void chargeAlipay(String imei, String os_version,
			String device_name, String device_brand, String appversion,
			String deviceID, String phoneNumber, String alipayAccount,
			String alipayName, SuccessCallBack successCallBack,
			FailCallBack failCallBack);

	/**
	 * Q币提现
	 * 
	 * @param imei
	 * @param os_version
	 * @param device_name
	 * @param device_brand
	 * @param appversion
	 * @param deviceID
	 * @param phoneNumber
	 * @param qq
	 * @param successCallBack
	 * @param failCallBack
	 */
	public void chargeQBee(String imei, String os_version, String device_name,
			String device_brand, String appversion, String deviceID,
			String phoneNumber, String qq, SuccessCallBack successCallBack,
			FailCallBack failCallBack);

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
		void onFail(String info);
	}
}