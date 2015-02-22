package com.wozuihighwa.supmission.activity;

import net.youmi.android.offers.OffersManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cn.aow.android.DAOW;
import cn.waps.AppConnect;

import com.dlnetwork.DevInit;
import com.wozuihighwa.supmission.R;
import com.wozuihighwa.supmission.utils.Utils;

public class TaskActivity extends ActionBarActivity implements
		OnItemClickListener {

	private ListView lv_taskList;
	private Toolbar toolbar;

	private static final String[] lvs = { "多盟任务", "有米任务", "点乐任务", "万普任务", };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task);
		// 找到布局中view
		findView();
		// 设置标题
		toolbar.setTitle("每日任务");
		// 设置标题颜色
		toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
		// 如果运行环境是5.0，使用Elevation这个特性
		Utils.isLolipop(toolbar);
		// 设置适配器
		lv_taskList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, lvs));
		lv_taskList.setOnItemClickListener(this);
	}

	private void findView() {
		lv_taskList = (ListView) findViewById(R.id.lv_taskList);
		toolbar = (Toolbar) findViewById(R.id.tb_custom);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:
			// 多盟_打开积分墙
			DAOW.getInstance(this).show(this);
			break;
		case 1:
			// 有米_打开积分墙
			OffersManager.getInstance(this).showOffersWall();
			break;
		case 2:
			// 点乐_打开积分墙
			DevInit.showOffers(this);
			break;
		case 3:
			// 万普_打开积分墙
			AppConnect.getInstance(this).showOffers(this);
			break;
		default:
			break;
		}
	}

}
