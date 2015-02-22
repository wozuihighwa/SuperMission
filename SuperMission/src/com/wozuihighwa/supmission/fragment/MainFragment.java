package com.wozuihighwa.supmission.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.faizmalkani.floatingactionbutton.FloatingActionButton;
import com.wozuihighwa.supmission.R;
import com.wozuihighwa.supmission.activity.TaskActivity;
import com.wozuihighwa.supmission.adapter.MainFragmentAdapter;
import com.wozuihighwa.supmission.utils.Utils;

public class MainFragment extends Fragment {

	private FloatingActionButton fab;
	private ListView lv_main_fragment;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		fab = (FloatingActionButton) rootView.findViewById(R.id.bt_fab);
		// 如果运行环境是5.0，则view使用Elevation特性
		Utils.isLolipop(rootView.findViewById(R.id.rl_balance));
		Utils.isLolipop(fab);

		lv_main_fragment = (ListView) rootView
				.findViewById(R.id.lv_main_fragment);
		lv_main_fragment.setAdapter(new MainFragmentAdapter(getActivity()));

		fab.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), TaskActivity.class));
			}
		});
		return rootView;
	}
}
