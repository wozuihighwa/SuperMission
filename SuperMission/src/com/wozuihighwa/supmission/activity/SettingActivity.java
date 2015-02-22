package com.wozuihighwa.supmission.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.wozuihighwa.supmission.R;
import com.wozuihighwa.supmission.utils.Utils;

public class SettingActivity extends ActionBarActivity implements
		DialogInterface.OnClickListener, AdapterView.OnItemClickListener {

	private Toolbar mToolbar;
	private ListView mBasicList;
	private ListView mMoreList;
	private AlertDialog mAlertDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		// 找到布局中view
		findView();
		// 设置标题
		mToolbar.setTitle("设置");
		// 设置标题颜色
		mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
		// 如果运行环境是5.0，使用Elevation这个特性
		Utils.isLolipop(mToolbar);
		// 为ListView控件设置适配器
		mBasicList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, basicItem()));
		mMoreList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, moreItem()));

		// 为ListView中的item设置点击事件监听
		mBasicList.setOnItemClickListener(this);
		mMoreList.setOnItemClickListener(this);

		// 获取SharedPreferences文件
		final SharedPreferences sharedPrefs = this.getSharedPreferences(
				"ConfigInfo", Context.MODE_PRIVATE);
		// 如果还未设置个人信息（手机号码）
		if (sharedPrefs.getString("phoneNumber", "").equals("")) {
			mAlertDialog = new AlertDialog.Builder(this).setTitle("提示")
					.setMessage("设置资料前，需要先绑定手机号。")
					.setPositiveButton("确认并去设置", this).create();
			mAlertDialog.show();
		}
	}

	private void findView() {
		mToolbar = (Toolbar) this.findViewById(R.id.tb_custom);
		mBasicList = (ListView) this.findViewById(R.id.lv_basicSetting);
		mMoreList = (ListView) this.findViewById(R.id.lv_moreSetting);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (parent.getId()) {
		case R.id.lv_basicSetting:
			if (position == 0) {
				startActivity(new Intent(this, UserInfoActivity.class));
			} else if (position == 1) {
				startActivity(new Intent(this, AlipayActivity.class));
			} else {
				startActivity(new Intent(this, QQActivity.class));
			}

			break;

		case R.id.lv_moreSetting:

			Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
			break;

		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		startActivity(new Intent(this, UserInfoActivity.class));
	}

	// “基本”设置
	private List<String> basicItem() {
		List<String> data = new ArrayList<String>();
		data.add("个人信息");
		data.add("支付宝信息");
		data.add("QQ信息");
		return data;
	}

	// “更多”设置
	private List<String> moreItem() {
		List<String> data = new ArrayList<String>();
		data.add("功能演示");
		data.add("问题反馈");
		data.add("客服QQ");
		data.add("关于我们");
		return data;
	}
}
