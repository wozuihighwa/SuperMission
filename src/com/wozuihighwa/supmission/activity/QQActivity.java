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
 * 设置QQ号码Activity
 * 
 * @author wozuihighwa
 * @date 2015-1-22
 */
public class QQActivity extends ActionBarActivity {

	private EditText et_inputQQ, et_confirmQQ;
	private Button bt_submitQQ;
	private SharedPreferences sharedPrefs;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qq);
		findView();
		// 设置toolbar标题
		toolbar.setTitle("QQ信息");
		toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
		// 如果运行环境是5.0，使用Elevation这个特性
		Utils.isLolipop(toolbar);
		// 找到SharedPreferences文件
		sharedPrefs = this.getSharedPreferences("ConfigInfo",
				Context.MODE_PRIVATE);
		if (sharedPrefs.getString("qq", "") != "") {
			et_inputQQ.setText(sharedPrefs.getString("qq", ""));
			et_confirmQQ.setText(sharedPrefs.getString("qq", ""));
		}
		bt_submitQQ.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String account = et_inputQQ.getText().toString();
				String confirm = et_confirmQQ.getText().toString();

				// 所填项不能为空
				if (account.trim().isEmpty() || confirm.trim().isEmpty()) {
					Toast.makeText(QQActivity.this, "信息不能为空!",
							Toast.LENGTH_SHORT).show();
					return;
				}
				// 如果两次账户输入不一致
				if (!account.equals(confirm)) {
					Toast.makeText(QQActivity.this, "两次输入号码不一致!",
							Toast.LENGTH_SHORT).show();
					return;
				}

				SettingService service = new SettingServiceImpl();

				String imei = sharedPrefs.getString("imei", "");
				String os_version = sharedPrefs.getString("os_version", "");
				String device_name = sharedPrefs.getString("device_name", "");
				String device_brand = sharedPrefs.getString("device_brand", "");
				String appversion = sharedPrefs.getString("appversion", "");
				String deviceID = sharedPrefs.getString("deviceID", "");
				String phoneNumber = sharedPrefs.getString("phoneNumber", "");

				service.setQQInfo(imei, os_version, device_name, device_brand,
						appversion, deviceID, phoneNumber, confirm,
						sharedPrefs.getString("realName", ""),
						sharedPrefs.getString("sex", ""),
						sharedPrefs.getString("age", ""),
						sharedPrefs.getString("email", ""), sharedPrefs,
						new SettingService.SuccessCallBack() {

							@Override
							public void onSuccess(String info) {
								Toast.makeText(QQActivity.this, info,
										Toast.LENGTH_SHORT).show();
								finish();
							}
						}, new SettingService.FailCallBack() {

							@Override
							public void onFail(String failInfo) {
								Toast.makeText(QQActivity.this, failInfo,
										Toast.LENGTH_SHORT).show();
							}
						});
			}

		});
	}

	private void findView() {
		toolbar = (Toolbar) findViewById(R.id.tb_custom);
		et_inputQQ = (EditText) findViewById(R.id.et_inputQQ);
		et_confirmQQ = (EditText) findViewById(R.id.et_confirmQQ);
		bt_submitQQ = (Button) findViewById(R.id.bt_submitQQ);
	}

}
