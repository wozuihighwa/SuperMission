package com.wozuihighwa.supmission.service.impl;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;

import com.wozuihighwa.supmission.utils.Config;
import com.wozuihighwa.supmission.net.NetConnection;
import com.wozuihighwa.supmission.service.IndexService;
import com.wozuihighwa.supmission.utils.MD5Util;

/**
 * 上传设备信息
 * 
 * @author wozuihighwa
 * @date 2015-1-23
 */
public class IndexServiceImpl implements IndexService {

	@Override
	public void fristUpdateDeviceInfo(final String imei,
			final String os_version, final String device_name,
			final String device_brand, final String appversion,
			final SharedPreferences sharedPrefs, final Context context,
			final SuccessCallBack successCallBack,
			final FailCallBack failCallBack) {

		String temp = imei + os_version + device_name + device_brand
				+ appversion + Config.VERIFY_PARAM;

		String verify = MD5Util.MD5Encode(temp, Config.CHARSET_UTF_8);

		new NetConnection(
				Config.URL_INDEX,
				Config.HTTP_POST,
				new NetConnection.SuccessCallBack() {

					@Override
					public void onSuccess(String result) {
						try {
							JSONObject jsonObj = new JSONObject(result);
							switch (jsonObj.getString("status")) {
							case Config.STATUS_SUCCESS:
								SharedPreferences.Editor editor = sharedPrefs
										.edit();
								JSONObject jsonData = jsonObj
										.getJSONObject("data");

								if (successCallBack != null) {

									editor.putString("imei", imei).apply();
									editor.putString("os_version", os_version);
									editor.putString("device_name", device_name);
									editor.putString("device_brand",
											device_brand);
									editor.putString("appversion", appversion);
									editor.putString("deviceID",
											jsonData.getString("deviceID"));
									editor.apply();
									// 说明手机被绑定
									if (jsonData.getString("bindStatus")
											.equals("0")) {
										// 将phoneNumber写入SharedPreferences
										editor.putString(
												"phoneNumber",
												jsonData.getString("phoneNumber"))
												.apply();

										successCallBack
												.onSuccess(Config.ALREADY_BIND);

									} else {
										successCallBack
												.onSuccess(Config.NOT_ALREADLY_BIND);
									}

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
				device_brand, Config.APP_VERSION, appversion, Config.VERIFY,
				verify);
	}

	@Override
	public void updateDeviceInfo(String imei, String os_version,
			String device_name, String device_brand, String appversion,
			String deviceID, final SuccessCallBack successCallBack,
			final FailCallBack failCallBack) {

		String temp = imei + os_version + device_name + device_brand
				+ appversion + deviceID + Config.VERIFY_PARAM;

		String verify = MD5Util.MD5Encode(temp, Config.CHARSET_UTF_8);

		new NetConnection(Config.URL_INDEX, Config.HTTP_POST,
				new NetConnection.SuccessCallBack() {

					@Override
					public void onSuccess(String result) {
						try {
							JSONObject jsonObj = new JSONObject(result);
							switch (jsonObj.getString("status")) {
							case Config.STATUS_SUCCESS:
								if (successCallBack != null) {
									successCallBack.onSuccess("Success!");
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
}
