package com.wozuihighwa.supmission.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

/**
 * 工具类
 * 
 * @author wozuihighwa
 * @date 2015-2-13
 */
public class Utils {
	/**
	 * 判断是否为Email
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {

		Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");
		Matcher m = p.matcher(email);
		boolean b = m.matches();
		if (b) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 如果运行环境是5.0，view使用Elevation这个特性
	 * 
	 * @param view
	 */
	@TargetApi(21)
	public static void isLolipop(View view) {
		if (Build.VERSION.SDK_INT == 21) {
			view.setElevation(15);
		}
	}
}
