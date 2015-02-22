package com.wozuihighwa.supmission.activity;

import com.wozuihighwa.supmission.R;
import com.wozuihighwa.supmission.utils.Utils;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

public class ChargeActivty extends ActionBarActivity {
	private Toolbar mToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charge);
		findView();
		mToolbar.setTitle("任务达人");// 设置标题
		mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
		// 如果运行环境是5.0，使用Elevation这个特性
		Utils.isLolipop(mToolbar);
	}

	private void findView() {
		mToolbar = (Toolbar) this.findViewById(R.id.tb_custom);
	}
}
