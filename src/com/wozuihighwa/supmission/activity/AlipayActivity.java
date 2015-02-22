package com.wozuihighwa.supmission.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wozuihighwa.supmission.R;
import com.wozuihighwa.supmission.service.SettingService;
import com.wozuihighwa.supmission.service.impl.SettingServiceImpl;
import com.wozuihighwa.supmission.utils.Utils;

/**
 * 设置支付宝账户Activity
 * 
 * @author wozuihighwa
 * @date 2015-1-22
 */
public class AlipayActivity extends ActionBarActivity {

	private EditText et_inputAlipay, et_confirmAlipay, et_alipayAccountName;
	private Button bt_submitAlipay;
	private SharedPreferences sharedPrefs;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alipay);
		// 初始化控件
		findView();
		// 设置toolbar标题
		toolbar.setTitle("支付宝信息");
		toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
		// 如果运行环境是5.0，使用Elevation这个特性
		Utils.isLolipop(toolbar);
		// 找到SharedPreferences文件
		sharedPrefs = this.getSharedPreferences("ConfigInfo",
				Context.MODE_PRIVATE);
		if (sharedPrefs.getString("alipayAccount", "") != "") {

			et_inputAlipay.setText(sharedPrefs.getString("alipayAccount", ""));
			et_confirmAlipay
					.setText(sharedPrefs.getString("alipayAccount", ""));
			et_alipayAccountName.setText(sharedPrefs
					.getString("alipayName", ""));
		}
		bt_submitAlipay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String alipayAccount = et_inputAlipay.getText().toString();
				String confirm = et_confirmAlipay.getText().toString();
				String accountName = et_alipayAccountName.getText().toString();
				// 所填项不能为空
				if (alipayAccount.trim().isEmpty() || confirm.trim().isEmpty()
						|| accountName.trim().isEmpty()) {
					Toast.makeText(AlipayActivity.this, "信息不能为空!",
							Toast.LENGTH_SHORT).show();
					return;
				}
				// 判断支付宝账户格式是否为邮箱或手机号码
				if (!(alipayAccount.trim().length() == 11 || Utils
						.isEmail(alipayAccount))) {
					Toast.makeText(AlipayActivity.this,
							"支付宝账户是邮箱地址或手机号码，请重新输入!", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				// 如果两次账户输入不一致
				if (!alipayAccount.equals(confirm)) {
					Toast.makeText(AlipayActivity.this, "两次输入账户不一致!",
							Toast.LENGTH_SHORT).show();
					return;
				}

				String imei = sharedPrefs.getString("imei", "");
				String os_version = sharedPrefs.getString("os_version", "");
				String device_name = sharedPrefs.getString("device_name", "");
				String device_brand = sharedPrefs.getString("device_brand", "");
				String appversion = sharedPrefs.getString("appversion", "");
				String deviceID = sharedPrefs.getString("deviceID", "");
				String phoneNumber = sharedPrefs.getString("phoneNumber", "");
				String realName = sharedPrefs.getString("realName", "");
				String sex = sharedPrefs.getString("sex", "");
				String age = sharedPrefs.getString("age", "");
				String email = sharedPrefs.getString("email", "");

				SettingService service = new SettingServiceImpl();

				service.setAlipayInfo(imei, os_version, device_name,
						device_brand, appversion, deviceID, phoneNumber,
						confirm, accountName, realName, sex, age, email,
						sharedPrefs, new SettingService.SuccessCallBack() {

							@Override
							public void onSuccess(String info) {
								Toast.makeText(AlipayActivity.this, info,
										Toast.LENGTH_SHORT).show();
								finish();
							}

						}, new SettingService.FailCallBack() {

							@Override
							public void onFail(String failInfo) {
								Toast.makeText(AlipayActivity.this, failInfo,
										Toast.LENGTH_SHORT).show();
							}
						});

			}

		});
	}

	// 初始化控件
	private void findView() {
		toolbar = (Toolbar) findViewById(R.id.tb_custom);
		et_inputAlipay = (EditText) findViewById(R.id.et_inputAlipay);
		et_confirmAlipay = (EditText) findViewById(R.id.et_confirmAlipay);
		et_alipayAccountName = (EditText) findViewById(R.id.et_alipayAccountName);
		bt_submitAlipay = (Button) findViewById(R.id.bt_submitAlipay);
	}
}
