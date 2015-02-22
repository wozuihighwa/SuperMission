package com.wozuihighwa.supmission.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络工具类
 * 
 * @author wozuihighwa
 * @date 2015-1-29
 */
public class NetWorkUtils {

	/**
	 * 检测手机是否联网
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnectted(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		// 获取当前网络信息
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;

	}

}
