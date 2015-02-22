package com.wozuihighwa.supmission.activity;

import net.youmi.android.offers.OffersManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cn.aow.android.DAOW;
import cn.waps.AppConnect;

import com.wozuihighwa.supmission.R;
import com.wozuihighwa.supmission.fragment.MainFragment;

public class MainActivity extends ActionBarActivity implements
		OnItemClickListener {

	private Toolbar toolbar;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView lvLeftMenu;
	private String[] lvs = { "首页", "提现", "设置" };
	private ArrayAdapter<String> arrayAdapter;
	private Fragment fragment;
	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 初始化，测试Publisher ID
		DAOW.getInstance(this).init(this, "96ZJ2b8QzehB3wTAwQ");

		findView();

		toolbar.setTitle("任务达人");// 设置标题
		toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

		// 将ToolBar设置为ActionBar
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);// 设置返回键可用
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// 创建返回键，并实现打开关/闭监听
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
				R.string.draw_open, R.string.draw_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				toolbar.setTitle("任务达人");
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}
		};
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		// 设置菜单列表
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, lvs);
		lvLeftMenu.setAdapter(arrayAdapter);
		// 设置监听事件
		lvLeftMenu.setOnItemClickListener(this);

		fragment = new MainFragment();
		fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.fl_home, fragment)
				.commit();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 回收有米资源
		OffersManager.getInstance(this).onAppExit();
		// 回收万普资源
		AppConnect.getInstance(this).close();

	}

	// 获取布局控件
	private void findView() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
		lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);
		toolbar = (Toolbar) findViewById(R.id.tb_custom);
	}

	// 监听Drawer中的点击事件
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0:
			// 首页
			fragment = new MainFragment();
			fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.fl_home, fragment)
					.commit();
			mDrawerLayout.closeDrawers();
			break;
		case 1:
			// 提现
			startActivity(new Intent(MainActivity.this, ChargeActivty.class));
			// fragment = new ChargeFragment();
			// fragmentManager = getSupportFragmentManager();
			// fragmentManager.beginTransaction().replace(R.id.fl_home,
			// fragment)
			// .commit();
			// toolbar.setTitle("提现");
			mDrawerLayout.closeDrawers();
			break;
		case 2:
			// 设置
			startActivity(new Intent(MainActivity.this, SettingActivity.class));
			// fragment = new SettingFragment();
			// fragmentManager = getSupportFragmentManager();
			// fragmentManager.beginTransaction().replace(R.id.fl_home,
			// fragment)
			// .commit();
			// toolbar.setTitle("设置");
			mDrawerLayout.closeDrawers();
			break;

		default:
			break;
		}
	}

}
