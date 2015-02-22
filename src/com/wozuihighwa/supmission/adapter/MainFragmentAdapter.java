package com.wozuihighwa.supmission.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wozuihighwa.supmission.R;

public class MainFragmentAdapter extends BaseAdapter {

	private Context context;

	public MainFragmentAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (position == 0) {
			convertView = View.inflate(context,
					R.layout.list_item_mainfragment1, null);
			return convertView;
		}

		if (position == 1) {
			convertView = View.inflate(context,
					R.layout.list_item_mainfragment2, null);
			return convertView;
		}
		if (position == 2) {
			convertView = View.inflate(context,
					R.layout.list_item_mainfragment3, null);
			return convertView;
		}

		return null;
	}

}
