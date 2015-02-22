package com.wozuihighwa.supmission.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.wozuihighwa.supmission.R;
import com.wozuihighwa.supmission.activity.AlipayActivity;
import com.wozuihighwa.supmission.activity.QQActivity;
import com.wozuihighwa.supmission.activity.UserInfoActivity;

public class SettingFragment extends Fragment implements
		DialogInterface.OnClickListener, AdapterView.OnItemClickListener {

	private ListView lv_basicSetting;
	private ListView lv_moreSetting;
	private AlertDialog dialog;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_setting, container,
				false);
		lv_basicSetting = (ListView) rootView
				.findViewById(R.id.lv_basicSetting);
		lv_moreSetting = (ListView) rootView.findViewById(R.id.lv_moreSetting);

		// 为ListView控件设置适配器
		lv_basicSetting.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, basicItem()));
		lv_moreSetting.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, moreItem()));

		// 为ListView中的item设置点击事件监听
		lv_basicSetting.setOnItemClickListener(this);
		lv_moreSetting.setOnItemClickListener(this);

		// 获取SharedPreferences文件
		final SharedPreferences sharedPrefs = getActivity()
				.getSharedPreferences("ConfigInfo", Context.MODE_PRIVATE);
		// 如果还未设置个人信息（手机号码）
		if (sharedPrefs.getString("phoneNumber", "").equals("")) {
			dialog = new AlertDialog.Builder(getActivity()).setTitle("提示")
					.setMessage("设置资料前，需要先绑定手机号。")
					.setPositiveButton("确认并去设置", this).create();
			dialog.show();
		}

		return rootView;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (parent.getId()) {
		case R.id.lv_basicSetting:
			if (position == 0) {
				startActivity(new Intent(getActivity(), UserInfoActivity.class));
			} else if (position == 1) {
				startActivity(new Intent(getActivity(), AlipayActivity.class));
			} else {
				startActivity(new Intent(getActivity(), QQActivity.class));
			}

			break;

		case R.id.lv_moreSetting:

			Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT)
					.show();
			break;

		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		startActivity(new Intent(getActivity(), UserInfoActivity.class));
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
