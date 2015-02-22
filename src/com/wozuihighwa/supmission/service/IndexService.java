package com.wozuihighwa.supmission.service;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 上传设备信息
 * 
 * @author wozuihighwa
 * @date 2015-1-23
 */
public interface IndexService {
	/**
	 * 首次更新设备信息
	 * 
	 * @return 返回服务器响应信息
	 */
	public void fristUpdateDeviceInfo(String imei, String os_version,
			String device_name, String device_brand, String appversion,
			SharedPreferences sharedPrefs, Context context,
			SuccessCallBack successCallBack, FailCallBack failCallBack);

	/**
	 * 更新设备信息
	 * 
	 * @return 返回服务器响应信息
	 */
	public void updateDeviceInfo(String imei, String os_version,
			String device_name, String device_brand, String appversion,
			String deviceID, SuccessCallBack successCallBack,
			FailCallBack failCallBack);

	/**
	 * 请求成功后的回调接口
	 */
	public static interface SuccessCallBack {
		void onSuccess(String data);
	}

	/**
	 * 请求失败的回调接口
	 */
	public static interface FailCallBack {
		void onFail(String data);
	}
}
