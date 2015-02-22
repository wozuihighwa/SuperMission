package com.wozuihighwa.supmission.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wozuihighwa.supmission.R;
import com.wozuihighwa.supmission.service.AccountService;
import com.wozuihighwa.supmission.service.impl.AccountServiceImpl;

public class ChargeFragment extends Fragment {

	private AccountService service;
	private SharedPreferences sharedPrefs;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.activity_charge, container,
				false);

		sharedPrefs = getActivity().getSharedPreferences("ConfigInfo",
				Context.MODE_PRIVATE);

		service = new AccountServiceImpl();

		service.whetherCharged(sharedPrefs.getString("imei", ""),
				sharedPrefs.getString("os_version", ""),
				sharedPrefs.getString("device_name", ""),
				sharedPrefs.getString("device_brand", ""),
				sharedPrefs.getString("appversion", ""),
				sharedPrefs.getString("deviceID", ""),
				sharedPrefs.getString("phoneNumber", ""), sharedPrefs, null,
				new AccountService.FailCallBack() {

					@Override
					public void onFail(String info) {
						Toast.makeText(getActivity(), info, Toast.LENGTH_SHORT)
								.show();
					}
				});

		return rootView;
	}

}
