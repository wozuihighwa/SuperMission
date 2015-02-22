package com.wozuihighwa.supmission.service.impl;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;

import com.wozuihighwa.supmission.utils.Config;
import com.wozuihighwa.supmission.net.NetConnection;
import com.wozuihighwa.supmission.service.SettingService;
import com.wozuihighwa.supmission.utils.MD5Util;

/**
 * 设置业务逻辑
 * 
 * @author wozuihighwa
 * @date 2015-1-24
 */
public class SettingServiceImpl implements SettingService {

	@Override
	public void bindAccount(String imei, String os_version, String device_name,
			String device_brand, String appversion, String deviceID,
			final String phoneNumber, final SharedPreferences sharedPrefs,
			final SuccessCallBack successCallBack,
			final FailCallBack failCallBack) {

		String temp = imei + os_version + device_name + device_brand
				+ appversion + deviceID + phoneNumber + Config.VERIFY_PARAM;

		String verify = MD5Util.MD5Encode(temp, Config.CHARSET_UTF_8);

		new NetConnection(Config.URL_BIND_ACCOUNT, Config.HTTP_POST,
				new NetConnection.SuccessCallBack() {

					@Override
					public void onSuccess(String result) {
						try {
							JSONObject jsonObj = new JSONObject(result);
							switch (jsonObj.getString("status")) {
							case Config.STATUS_SUCCESS:
								if (successCallBack != null) {
									successCallBack.onSuccess(jsonObj
											.getString("info"));
									sharedPrefs
											.edit()
											.putString("phoneNumber",
													phoneNumber).apply();
								}
								break;
							case Config.STATUS_FAIL:
								if (failCallBack != null) {
									failCallBack.onFail(jsonObj
											.getString("info"));
								}
								break;
							default:
								break;
							}

						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				}, new NetConnection.FailCallBack() {

					@Override
					public void onFail(String errorInfo) {
						if (failCallBack != null) {
							failCallBack.onFail(errorInfo);
						}
					}
				}, Config.IMEI, imei, Config.OS_VERSION, os_version,
				Config.DEVICE_NAME, device_name, Config.DEVICE_BRAND,
				device_brand, Config.APP_VERSION, appversion, Config.DEVICE_ID,
				deviceID, Config.PHONE_NUMBER, phoneNumber, Config.VERIFY,
				verify);

	}

	@Override
	public void getUserInfo(String imei, String os_version, String device_name,
			String device_brand, String appversion, String deviceID,
			String phoneNumber, final SuccessCallBack successCallBack,
			final FailCallBack failCallBack) {

		String temp = imei + os_version + device_name + device_brand
				+ appversion + deviceID + phoneNumber + Config.VERIFY_PARAM;

		String verify = MD5Util.MD5Encode(temp, Config.CHARSET_UTF_8);

		new NetConnection(Config.URL_GET_USER_INFO, Config.HTTP_POST,
				new NetConnection.SuccessCallBack() {

					@Override
					public void onSuccess(String result) {

						if (successCallBack != null) {
							try {
								JSONObject jsonObj = new JSONObject(result);
								switch (jsonObj.getString("status")) {
								case Config.STATUS_SUCCESS:
									if (successCallBack != null) {
										JSONObject jsonData = jsonObj
												.getJSONObject("data");

										successCallBack.onSuccess(jsonData
												.toString());
									}
									break;
								case Config.STATUS_FAIL:
									if (failCallBack != null) {
										failCallBack.onFail(jsonObj
												.getString("info"));
									}
									break;
								default:
									break;
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					}
				}, new NetConnection.FailCallBack() {

					@Override
					public void onFail(String errorInfo) {
						if (failCallBack != null) {
							failCallBack.onFail(errorInfo);
						}
					}
				}, Config.IMEI, imei, Config.OS_VERSION, os_version,
				Config.DEVICE_NAME, device_name, Config.DEVICE_BRAND,
				device_brand, Config.APP_VERSION, appversion, Config.DEVICE_ID,
				deviceID, Config.PHONE_NUMBER, phoneNumber, Config.VERIFY,
				verify);
	}

	@Override
	public void setUserInfo(String imei, String os_version, String device_name,
			String device_brand, String appversion, String deviceID,
			String phoneNumber, final String realName, final String sex,
			final String age, final String email,
			final SharedPreferences sharedPrefs,
			final SuccessCallBack successCallBack,
			final FailCallBack failCallBack) {

		String temp = imei + os_version + device_name + device_brand
				+ appversion + deviceID + phoneNumber + realName + sex + age
				+ email + Config.VERIFY_PARAM;

		String verify = MD5Util.MD5Encode(temp, Config.CHARSET_UTF_8);

		new NetConnection(
				Config.URL_SET_USER_INFO,
				Config.HTTP_POST,
				new NetConnection.SuccessCallBack() {

					@Override
					public void onSuccess(String result) {
						if (successCallBack != null) {
							try {
								JSONObject jsonObj = new JSONObject(result);
								switch (jsonObj.getString("status")) {
								case Config.STATUS_SUCCESS:
									if (successCallBack != null) {

										SharedPreferences.Editor editor = sharedPrefs
												.edit();
										editor.putString("realName", realName);
										editor.putString("sex", sex);
										editor.putString("age", age);
										editor.putString("email", email);

										editor.apply();

										successCallBack.onSuccess(jsonObj
												.getString("info"));
									}
									break;
								case Config.STATUS_FAIL:
									if (failCallBack != null) {
										failCallBack.onFail(jsonObj
												.getString("info"));
									}
									break;
								default:
									break;
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					}
				}, new NetConnection.FailCallBack() {

					@Override
					public void onFail(String errorInfo) {
						if (failCallBack != null) {
							failCallBack.onFail(errorInfo);
						}
					}
				}, Config.IMEI, imei, Config.OS_VERSION, os_version,
				Config.DEVICE_NAME, device_name, Config.DEVICE_BRAND,
				device_brand, Config.APP_VERSION, appversion, Config.DEVICE_ID,
				deviceID, Config.PHONE_NUMBER, phoneNumber, Config.REAL_NAME,
				realName, Config.SEX, sex, Config.AGE, age, Config.EMAIL,
				email, Config.VERIFY, verify);
	}

	@Override
	public void setAlipayInfo(String imei, String os_version,
			String device_name, String device_brand, String appversion,
			String deviceID, String phoneNumber, final String alipayAccount,
			final String alipayName, String realName, String sex, String age,
			String email, final SharedPreferences sharedPrefs,
			final SuccessCallBack successCallBack,
			final FailCallBack failCallBack) {

		String temp = imei + os_version + device_name + device_brand
				+ appversion + deviceID + phoneNumber + alipayAccount
				+ alipayName + realName + sex + age + email
				+ Config.VERIFY_PARAM;

		String verify = MD5Util.MD5Encode(temp, Config.CHARSET_UTF_8);

		new NetConnection(
				Config.URL_SET_ALIPAY_ACCOUNT,
				Config.HTTP_POST,
				new NetConnection.SuccessCallBack() {

					@Override
					public void onSuccess(String result) {

						try {
							JSONObject jsonObj = new JSONObject(result);
							switch (jsonObj.getString("status")) {
							case Config.STATUS_SUCCESS:
								if (successCallBack != null) {

									SharedPreferences.Editor editor = sharedPrefs
											.edit();
									editor.putString("alipayAccount",
											alipayAccount);
									editor.putString("alipayName", alipayName);

									editor.apply();

									successCallBack.onSuccess(jsonObj
											.getString("info"));
								}
								break;
							case Config.STATUS_FAIL:
								if (failCallBack != null) {
									failCallBack.onFail(jsonObj
											.getString("info"));
								}
								break;
							default:
								break;
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				}, new NetConnection.FailCallBack() {

					@Override
					public void onFail(String errorInfo) {
						if (failCallBack != null) {
							failCallBack.onFail(errorInfo);
						}
					}
				}, Config.IMEI, imei, Config.OS_VERSION, os_version,
				Config.DEVICE_NAME, device_name, Config.DEVICE_BRAND,
				device_brand, Config.APP_VERSION, appversion, Config.DEVICE_ID,
				deviceID, Config.PHONE_NUMBER, phoneNumber,
				Config.ALIPAY_ACCOUNT, alipayAccount, Config.ALIPAY_NAME,
				alipayName, Config.REAL_NAME, realName, Config.SEX, sex,
				Config.AGE, age, Config.EMAIL, email, Config.VERIFY, verify);

	}

	@Override
	public void setQQInfo(String imei, String os_version, String device_name,
			String device_brand, String appversion, String deviceID,
			String phoneNumber, final String qq, String realName, String sex,
			String age, String email, final SharedPreferences sharedPrefs,
			final SuccessCallBack successCallBack,
			final FailCallBack failCallBack) {

		String temp = imei + os_version + device_name + device_brand
				+ appversion + deviceID + phoneNumber + qq + realName + sex
				+ age + email + Config.VERIFY_PARAM;

		String verify = MD5Util.MD5Encode(temp, Config.CHARSET_UTF_8);

		new NetConnection(
				Config.URL_SET_QQ_NUMBER,
				Config.HTTP_POST,
				new NetConnection.SuccessCallBack() {

					@Override
					public void onSuccess(String result) {

						try {
							JSONObject jsonObj = new JSONObject(result);
							switch (jsonObj.getString("status")) {
							case Config.STATUS_SUCCESS:
								if (successCallBack != null) {

									SharedPreferences.Editor editor = sharedPrefs
											.edit();
									editor.putString("qq", qq);

									editor.apply();

									successCallBack.onSuccess(jsonObj
											.getString("info"));
								}
								break;
							case Config.STATUS_FAIL:
								if (failCallBack != null) {
									failCallBack.onFail(jsonObj
											.getString("info"));
								}
								break;
							default:
								break;
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				}, new NetConnection.FailCallBack() {

					@Override
					public void onFail(String errorInfo) {
						if (failCallBack != null) {
							failCallBack.onFail(errorInfo);
						}
					}
				}, Config.IMEI, imei, Config.OS_VERSION, os_version,
				Config.DEVICE_NAME, device_name, Config.DEVICE_BRAND,
				device_brand, Config.APP_VERSION, appversion, Config.DEVICE_ID,
				deviceID, Config.PHONE_NUMBER, phoneNumber, Config.QQ_NUM, qq,
				Config.REAL_NAME, realName, Config.SEX, sex, Config.AGE, age,
				Config.EMAIL, email, Config.VERIFY, verify);

	}
}
