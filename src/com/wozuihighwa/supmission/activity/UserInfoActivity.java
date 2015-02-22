package com.wozuihighwa.supmission.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.wozuihighwa.supmission.R;
import com.wozuihighwa.supmission.service.SettingService;
import com.wozuihighwa.supmission.service.impl.SettingServiceImpl;
import com.wozuihighwa.supmission.utils.Utils;

/**
 * 设置个人资料Activity
 * 
 * @author wozuihighwa
 * @date 2015-1-22
 */
public class UserInfoActivity extends ActionBarActivity implements
		View.OnClickListener {

	private EditText et_myName, et_myPhoneNum, et_myEmail;
	private RadioButton rb_male, rb_female, rb_70s, rb_80s, rb_90s, rb_00s;
	private Button bt_submitInfo, bt_bindPhoneNum;
	private SharedPreferences sharedPrefs;
	private SettingService service;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userinfo);
		// 找到Layout中的控件
		findView();
		// 设置toolbar标题
		toolbar.setTitle("个人信息");
		toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
		// 如果运行环境是5.0，使用Elevation这个特性
		Utils.isLolipop(toolbar);
		// 找到SharedPreferences文件
		sharedPrefs = this.getSharedPreferences("ConfigInfo",
				Context.MODE_PRIVATE);
		// 如果已绑定手机号码
		if (sharedPrefs.getString("phoneNumber", "") != "") {
			et_myPhoneNum.setText(sharedPrefs.getString("phoneNumber", ""));
			et_myPhoneNum.setFocusable(false);
			et_myPhoneNum.setEnabled(false);
			bt_bindPhoneNum.setVisibility(View.GONE);

			et_myName.setText(sharedPrefs.getString("realName", ""));
			et_myEmail.setText(sharedPrefs.getString("email", ""));

			// 判断性别
			if (sharedPrefs.getString("sex", "").equals("1")) {
				rb_male.setChecked(true);
			} else if (sharedPrefs.getString("sex", "").equals("0")) {
				rb_female.setChecked(true);
			}

			// 判断年龄
			if (sharedPrefs.getString("age", "").equals("70后")) {
				rb_70s.setChecked(true);
			} else if (sharedPrefs.getString("age", "").equals("80后")) {
				rb_80s.setChecked(true);
			} else if (sharedPrefs.getString("age", "").equals("90后")) {
				rb_90s.setChecked(true);
			} else {
				rb_00s.setChecked(true);
			}
		}

		service = new SettingServiceImpl();
		// 绑定手机号码
		bt_bindPhoneNum.setOnClickListener(this);
		// 按钮点击事件
		bt_submitInfo.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_bindPhoneNum:

			String phoneNumStr = et_myPhoneNum.getText().toString();
			// 如果手机号码不符合规范
			if (!isPhoneNum(phoneNumStr)) {
				Toast.makeText(UserInfoActivity.this, "手机号码格式不对，请重新输入",
						Toast.LENGTH_SHORT).show();
			} else {

				// 绑定手机号码
				service.bindAccount(sharedPrefs.getString("imei", ""),
						sharedPrefs.getString("os_version", ""),
						sharedPrefs.getString("device_name", ""),
						sharedPrefs.getString("device_brand", ""),
						sharedPrefs.getString("appversion", ""),
						sharedPrefs.getString("deviceID", ""), phoneNumStr,
						sharedPrefs, new SettingService.SuccessCallBack() {

							@Override
							public void onSuccess(String info) {
								Toast.makeText(UserInfoActivity.this, info,
										Toast.LENGTH_SHORT).show();

								bt_bindPhoneNum.post(new Runnable() {

									@Override
									public void run() {
										// 更新UI线程上的控件内容
										et_myPhoneNum.setText(sharedPrefs
												.getString("phoneNumber", ""));
										et_myPhoneNum.setFocusable(false);
										et_myPhoneNum.setEnabled(false);
										bt_bindPhoneNum
												.setVisibility(View.GONE);
									}
								});

							}
						}, new SettingService.FailCallBack() {

							@Override
							public void onFail(String failInfo) {
								Toast.makeText(UserInfoActivity.this, failInfo,
										Toast.LENGTH_SHORT).show();
							}
						});
			}
			break;

		case R.id.bt_submitMyInfo:

			if (et_myPhoneNum.getText().toString().equals("")) {
				Toast.makeText(UserInfoActivity.this, "手机号码不能为空！",
						Toast.LENGTH_SHORT).show();
			}
			String imei = sharedPrefs.getString("imei", "");
			String os_version = sharedPrefs.getString("os_version", "");
			String device_name = sharedPrefs.getString("device_name", "");
			String device_brand = sharedPrefs.getString("device_brand", "");
			String appversion = sharedPrefs.getString("appversion", "");
			String deviceID = sharedPrefs.getString("deviceID", "");
			String phoneNumber = et_myPhoneNum.getText().toString();
			String realName = et_myName.getText().toString();
			String sex = getSex();
			String age = getAge();
			String email = et_myEmail.getText().toString();

			service.setUserInfo(imei, os_version, device_name, device_brand,
					appversion, deviceID, phoneNumber, realName, sex, age,
					email, sharedPrefs, new SettingService.SuccessCallBack() {

						@Override
						public void onSuccess(String info) {
							Toast.makeText(UserInfoActivity.this, info,
									Toast.LENGTH_SHORT).show();
						}
					}, new SettingService.FailCallBack() {

						@Override
						public void onFail(String failInfo) {
							Toast.makeText(UserInfoActivity.this, failInfo,
									Toast.LENGTH_SHORT).show();
						}
					});

			finish();

			break;

		default:
			break;
		}

	}

	// 找到Layout中的控件
	private void findView() {
		toolbar = (Toolbar) findViewById(R.id.tb_custom);
		et_myName = (EditText) findViewById(R.id.et_myName);
		rb_male = (RadioButton) findViewById(R.id.rb_male);
		rb_female = (RadioButton) findViewById(R.id.rb_female);
		rb_70s = (RadioButton) findViewById(R.id.rb_70s);
		rb_80s = (RadioButton) findViewById(R.id.rb_80s);
		rb_90s = (RadioButton) findViewById(R.id.rb_90s);
		rb_00s = (RadioButton) findViewById(R.id.rb_00s);
		et_myPhoneNum = (EditText) findViewById(R.id.et_myPhoneNum);
		et_myEmail = (EditText) findViewById(R.id.et_myEmail);
		bt_submitInfo = (Button) findViewById(R.id.bt_submitMyInfo);
		bt_bindPhoneNum = (Button) findViewById(R.id.bt_bindPhoneNum);
	}

	// 获取性别
	private String getSex() {
		String sex;
		if (rb_male.isChecked()) {
			sex = "1";
		} else {
			sex = "2";
		}
		return sex;
	}

	// 获取年龄
	private String getAge() {
		String age;
		if (rb_70s.isChecked()) {
			age = rb_70s.getText().toString();
		} else if (rb_80s.isChecked()) {
			age = rb_80s.getText().toString();
		} else if (rb_90s.isChecked()) {
			age = rb_90s.getText().toString();
		} else {
			age = rb_00s.getText().toString();
		}
		return age;
	}

	// 判断号码是否正确
	private boolean isPhoneNum(String phoneNumStr) {
		// 如果号码是11位
		if (phoneNumStr.trim().length() == 11) {
			return true;
		}
		return false;
	}
}
