package com.wozuihighwa.supmission.service.impl;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;

import com.wozuihighwa.supmission.utils.Config;
import com.wozuihighwa.supmission.net.NetConnection;
import com.wozuihighwa.supmission.service.AccountService;
import com.wozuihighwa.supmission.utils.MD5Util;

/**
 * 用户账户业务逻辑实现类
 * 
 * @author wozuihighwa
 * @date 2015-1-26
 */
public class AccountServiceImpl implements AccountService {

	@Override
	public void whetherCharged(String imei, String os_version,
			String device_name, String device_brand, String appversion,
			String deviceID, String phoneNumber,
			final SharedPreferences sharedPrefs,
			final SuccessCallBack successCallBack,
			final FailCallBack failCallBack) {

		String temp = imei + os_version + device_name + device_brand
				+ appversion + deviceID + phoneNumber + Config.VERIFY_PARAM;

		String verify = MD5Util.MD5Encode(temp, Config.CHARSET_UTF_8);

		new NetConnection(
				Config.URL_WHETHER_CHARGED,
				Config.HTTP_POST,
				new NetConnection.SuccessCallBack() {

					@Override
					public void onSuccess(String result) {
						try {
							JSONObject jsonObj = new JSONObject(result);
							switch (jsonObj.getString("status")) {
							case Config.STATUS_SUCCESS:
								if (successCallBack != null) {
									JSONObject data = jsonObj
											.getJSONObject("data");
									SharedPreferences.Editor editor = sharedPrefs
											.edit();
									editor.putString("firstCharge",
											data.getString("firstCharge"));
									editor.putString("weekCharged",
											data.getString("weekCharged"));
									editor.putString("limitMoney",
											data.getString("limitMoney"));

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
				deviceID, Config.PHONE_NUMBER, phoneNumber, Config.VERIFY,
				verify);
	}

	@Override
	public void getAccountInfo(String imei, String os_version,
			String device_name, String device_brand, String appversion,
			String deviceID, final SharedPreferences sharedPrefs,
			final SuccessCallBack successCallBack,
			final FailCallBack failCallBack) {

		String temp = imei + os_version + device_name + device_brand
				+ appversion + deviceID + Config.VERIFY_PARAM;

		String verify = MD5Util.MD5Encode(temp, Config.CHARSET_UTF_8);

		new NetConnection(
				Config.URL_GET_ACCOUNT_INFO,
				Config.HTTP_POST,
				new NetConnection.SuccessCallBack() {

					@Override
					public void onSuccess(String result) {
						try {
							JSONObject jsonObj = new JSONObject(result);
							switch (jsonObj.getString("status")) {
							case Config.STATUS_SUCCESS:
								if (successCallBack != null) {
									JSONObject jsonData = jsonObj
											.getJSONObject("data");
									SharedPreferences.Editor editor = sharedPrefs
											.edit();
									editor.putString("award",
											jsonData.getString("award"));
									editor.putString("charge",
											jsonData.getString("charge"));
									editor.putString("money",
											jsonData.getString("money"));

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
				deviceID, Config.VERIFY, verify);
	}

	@Override
	public void chargeMobile(String imei, String os_version,
			String device_name, String device_brand, String appversion,
			String deviceID, String phoneNumber, String realName, String sex,
			String age, String email, final SuccessCallBack successCallBack,
			final FailCallBack failCallBack) {

		String temp = imei + os_version + device_name + device_brand
				+ appversion + deviceID + phoneNumber + realName + sex + age
				+ email + Config.VERIFY_PARAM;

		String verify = MD5Util.MD5Encode(temp, Config.CHARSET_UTF_8);

		new NetConnection(Config.URL_CHARGE_MOBILE, Config.HTTP_POST,
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
				deviceID, Config.PHONE_NUMBER, phoneNumber, Config.REAL_NAME,
				realName, Config.SEX, sex, Config.AGE, age, Config.EMAIL,
				email, Config.VERIFY, verify);
	}

	@Override
	public void chargeAlipay(String imei, String os_version,
			String device_name, String device_brand, String appversion,
			String deviceID, String phoneNumber, String alipayAccount,
			String alipayName, final SuccessCallBack successCallBack,
			final FailCallBack failCallBack) {

		String temp = imei + os_version + device_name + device_brand
				+ appversion + deviceID + phoneNumber + alipayAccount
				+ alipayName + Config.VERIFY_PARAM;

		String verify = MD5Util.MD5Encode(temp, Config.CHARSET_UTF_8);

		new NetConnection(Config.URL_CHARGE_MOBILE, Config.HTTP_POST,
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
				alipayName, Config.VERIFY, verify);

	}

	@Override
	public void chargeQBee(String imei, String os_version, String device_name,
			String device_brand, String appversion, String deviceID,
			String phoneNumber, String qq,
			final SuccessCallBack successCallBack,
			final FailCallBack failCallBack) {

		String temp = imei + os_version + device_name + device_brand
				+ appversion + deviceID + phoneNumber + qq
				+ Config.VERIFY_PARAM;

		String verify = MD5Util.MD5Encode(temp, Config.CHARSET_UTF_8);

		new NetConnection(Config.URL_CHARGE_MOBILE, Config.HTTP_POST,
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
				Config.VERIFY, verify);
	}

}
