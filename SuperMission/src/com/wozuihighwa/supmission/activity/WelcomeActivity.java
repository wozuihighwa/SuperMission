package com.wozuihighwa.supmission.activity;

import net.youmi.android.AdManager;
import net.youmi.android.offers.OffersManager;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import cn.aow.android.DAOW;
import cn.waps.AppConnect;

import com.dlnetwork.DevInit;
import com.wozuihighwa.supmission.R;
import com.wozuihighwa.supmission.net.NetWorkUtils;
import com.wozuihighwa.supmission.service.AccountService;
import com.wozuihighwa.supmission.service.AccountService.FailCallBack;
import com.wozuihighwa.supmission.service.IndexService;
import com.wozuihighwa.supmission.service.SettingService;
import com.wozuihighwa.supmission.service.impl.AccountServiceImpl;
import com.wozuihighwa.supmission.service.impl.IndexServiceImpl;
import com.wozuihighwa.supmission.service.impl.SettingServiceImpl;
import com.wozuihighwa.supmission.utils.Config;

/**
 * 欢迎页面Activity
 * 
 * @author wozuihighwa
 * @date 2015-1-22
 */
public class WelcomeActivity extends Activity {

	private SharedPreferences sharedPrefs;
	private IndexService indexService;
	private AccountService accountService;
	private SettingService settingService;
	private String deviceID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 取消标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 设置布局
		setContentView(R.layout.activity_welcome);
		// 检测手机是否联网
		boolean flag = NetWorkUtils.isConnectted(this);
		// 手机已联网
		if (flag) {
			try {
				// 获取手机管理
				TelephonyManager tm = (TelephonyManager) this
						.getSystemService(Context.TELEPHONY_SERVICE);
				// 获取包管理
				PackageInfo packageInfo = getPackageManager().getPackageInfo(
						this.getPackageName(), 0);
				// 上传设备信息
				dataExchange(tm, packageInfo);

			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			Toast.makeText(this, "无法联网，请检查网络。", Toast.LENGTH_SHORT).show();
		}

		// 初始化多盟组件，测试Publisher ID
		DAOW.getInstance(this).init(this, "96ZJ2b8QzehB3wTAwQ");

		// 初始化有米组件
		AdManager.getInstance(this).init(Config.YOUMI_APPID,
				Config.YOUMI_APPSECRET, false);
		OffersManager.getInstance(this).onAppLaunch();

		// 初始化点乐组件
		DevInit.initGoogleContext(this, Config.DIANJOY_APPID);

		// 初始化万普组件
		AppConnect.getInstance(Config.WAPS_APPID, Config.WAPS_APPPID, this);

		// 延时3秒关闭欢迎页面，并跳转到主Activity
		delaySkip();

	}

	/**
	 * 上传设备信息、获取用户账户信息
	 * 
	 * @param tm
	 * @param packageInfo
	 * @return
	 */
	private void dataExchange(TelephonyManager tm, PackageInfo packageInfo) {

		final String imei = tm.getDeviceId();
		final String os_version = android.os.Build.VERSION.RELEASE;
		final String device_name = android.os.Build.MODEL;
		final String device_brand = android.os.Build.MANUFACTURER;
		final String appversion = packageInfo.versionName;

		// 判断是否为第一次登陆
		sharedPrefs = WelcomeActivity.this.getSharedPreferences("ConfigInfo",
				Context.MODE_PRIVATE);
		deviceID = sharedPrefs.getString("deviceID", "");
		indexService = new IndexServiceImpl();
		accountService = new AccountServiceImpl();
		settingService = new SettingServiceImpl();
		// 如果是第一次打开App
		if (deviceID == "") {

			indexService.fristUpdateDeviceInfo(imei, os_version, device_name,
					device_brand, appversion, sharedPrefs,
					WelcomeActivity.this, new IndexService.SuccessCallBack() {

						@Override
						public void onSuccess(String data) {

							Toast.makeText(WelcomeActivity.this, data,
									Toast.LENGTH_SHORT).show();

							// 如果手机之前绑定过
							if (data.equals(Config.ALREADY_BIND)) {
								settingService.getUserInfo(imei, os_version,
										device_name, device_brand, appversion,
										device_brand, sharedPrefs.getString(
												"phoneNumber", ""),
										new SettingService.SuccessCallBack() {

											@Override
											public void onSuccess(String info) {
												try {
													JSONObject jsonData = new JSONObject(
															info);

													SharedPreferences.Editor editor = sharedPrefs
															.edit();
													editor.putString(
															"realName",
															jsonData.getString("realname"));
													editor.putString(
															"age",
															jsonData.getString("age"));
													editor.putString(
															"sex",
															jsonData.getString("sex"));
													editor.putString(
															"email",
															jsonData.getString("email"));
													editor.putString(
															"alipayAccount",
															jsonData.getString("alipayAccount"));
													editor.putString(
															"alipayName",
															jsonData.getString("alipayName"));
													editor.putString(
															"qq",
															jsonData.getString("qq"));
													editor.apply();
												} catch (JSONException e) {
													e.printStackTrace();
												}

											}
										}, new SettingService.FailCallBack() {

											@Override
											public void onFail(String failInfo) {

											}
										});
							}
							accountService.getAccountInfo(imei, os_version,
									device_name, device_brand, appversion,
									sharedPrefs.getString("deviceID", ""),
									sharedPrefs,
									new AccountService.SuccessCallBack() {

										@Override
										public void onSuccess(String info) {
											// 将money存入SharedPreferences中
											SharedPreferences.Editor editor = sharedPrefs
													.edit();
											editor.putString("money", info);
											editor.apply();
										}
									}, new AccountService.FailCallBack() {

										@Override
										public void onFail(String info) {
											Toast.makeText(
													WelcomeActivity.this, info,
													Toast.LENGTH_SHORT).show();
										}
									});

						}

					}, new IndexService.FailCallBack() {

						@Override
						public void onFail(String failInfo) {
							Toast.makeText(WelcomeActivity.this, failInfo,
									Toast.LENGTH_SHORT).show();
						}
					});

		} else {

			indexService.updateDeviceInfo(imei, os_version, device_name,
					device_brand, appversion, deviceID,
					new IndexService.SuccessCallBack() {

						@Override
						public void onSuccess(String info) {
							Toast.makeText(WelcomeActivity.this, info,
									Toast.LENGTH_SHORT).show();
						}
					}, new IndexService.FailCallBack() {

						@Override
						public void onFail(String failInfo) {
							Toast.makeText(WelcomeActivity.this, failInfo,
									Toast.LENGTH_SHORT).show();
						}
					});

			accountService.getAccountInfo(imei, os_version, device_name,
					device_brand, appversion, deviceID, sharedPrefs,
					new AccountService.SuccessCallBack() {

						@Override
						public void onSuccess(String info) {

						}
					}, new FailCallBack() {

						@Override
						public void onFail(String info) {
							Toast.makeText(WelcomeActivity.this, info,
									Toast.LENGTH_SHORT).show();
						}
					});

			settingService.getUserInfo(imei, os_version, device_name,
					device_brand, appversion, deviceID,
					sharedPrefs.getString("phoneNumber", ""),

					null, new SettingService.FailCallBack() {

						@Override
						public void onFail(String failInfo) {
							Toast.makeText(WelcomeActivity.this, failInfo,
									Toast.LENGTH_SHORT).show();
						}
					});

		}

	}

	/**
	 * 延时3秒关闭欢迎页面，并跳转到主Activity
	 */
	private void delaySkip() {

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// 跳转至主界面
				startActivity(new Intent(WelcomeActivity.this,
						MainActivity.class));

				// 销毁掉该Activity
				finish();
			}
		}, 3000);
	}

}