package com.wozuihighwa.supmission.activity;

import net.youmi.android.offers.OffersManager;
import net.youmi.android.offers.PointsManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.aow.android.DAOW;
import cn.aow.android.DListener;

import com.dlnetwork.DevInit;
import com.dlnetwork.GetTotalMoneyListener;
import com.dlnetwork.SpendMoneyListener;
import com.wozuihighwa.supmission.R;

public class ChargeTest extends ActionBarActivity {

	private Button bt_duomeng, bt_duomeng2, bt_youmi, bt_youmi2;
	private TextView tv_duomeng;
	private TextView tv_youmi;
	private EditText et_duomeng;
	private EditText et_youmi;
	private Button bt_dianle;
	private Button bt_dianle2;
	private TextView tv_dianle;
	private EditText et_dianle;
	private SharedPreferences sharedPref;
	private OffersManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.charge_test);

		manager = OffersManager.getInstance(ChargeTest.this);

		bt_duomeng = (Button) findViewById(R.id.bt_duomeng);
		bt_duomeng2 = (Button) findViewById(R.id.bt_duomeng2);
		bt_youmi = (Button) findViewById(R.id.bt_youmi);
		bt_youmi2 = (Button) findViewById(R.id.bt_youmi2);
		bt_dianle = (Button) findViewById(R.id.bt_dianle);
		bt_dianle2 = (Button) findViewById(R.id.bt_dianle2);

		tv_duomeng = (TextView) findViewById(R.id.tv_duomeng);
		tv_youmi = (TextView) findViewById(R.id.tv_youmi);
		tv_dianle = (TextView) findViewById(R.id.tv_dianle);

		et_duomeng = (EditText) findViewById(R.id.et_duomeng);
		et_youmi = (EditText) findViewById(R.id.et_youmi);
		et_dianle = (EditText) findViewById(R.id.et_dianle);

		sharedPref = getSharedPreferences("ConfigInfo", Context.MODE_PRIVATE);

		bt_duomeng.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkPoints();
			}
		});

		bt_duomeng2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		bt_youmi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				manager.setCustomUserId(sharedPref.getString("deviceID", ""));
				// 有米Android SDK v4.10之后的sdk还需要配置下面代码，以告诉sdk使用了服务器回调
				manager.setUsingServerCallBack(true);
				int myPointBalance = PointsManager.getInstance(ChargeTest.this)
						.queryPoints();
				tv_youmi.setText("" + myPointBalance);
			}
		});

		bt_youmi2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				manager.setCustomUserId(sharedPref.getString("deviceID", ""));
				// 有米Android SDK v4.10之后的sdk还需要配置下面代码，以告诉sdk使用了服务器回调
				manager.setUsingServerCallBack(true);

				boolean isSuccess = PointsManager
						.getInstance(ChargeTest.this)
						.spendPoints(
								Integer.parseInt(et_youmi.getText().toString()));
				if (isSuccess) {
					Toast.makeText(
							ChargeTest.this,
							"消费积分:"
									+ Integer.parseInt(et_youmi.getText()
											.toString()), Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

		bt_dianle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DevInit.getTotalMoney(ChargeTest.this,
						new GetTotalMoneyListener() {

							@Override
							public void getTotalMoneySuccessed(String arg0,
									long arg1) {
								tv_dianle.setText("" + arg1);
							}

							@Override
							public void getTotalMoneyFailed(String arg0) {
								tv_dianle.setText("获取失败");
							}
						});
			}
		});

		bt_dianle2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DevInit.spendMoney(ChargeTest.this,
						Integer.parseInt(et_dianle.getText().toString()),
						new SpendMoneyListener() {

							@Override
							public void spendMoneySuccess(long arg0) {
								tv_dianle.setText("余额:" + arg0);
							}

							@Override
							public void spendMoneyFailed(String arg0) {
								tv_dianle.setText("兑换失败");
							}
						});

			}
		});

	}

	/**
	 * 查询积分
	 */
	private void checkPoints() {
		DAOW.getInstance(this).checkPoints(new DListener() {
			@Override
			public void onResponse(Object... point) {
				// 用户总的积分数
				Integer totalPoint = (Integer) point[1]; // 用户的已消费积分数
				Integer consumPoint = (Integer) point[0]; // 用户的剩余积分数
				int lastPoint = totalPoint - consumPoint;
				tv_duomeng.setText("总积分:" + totalPoint + "\n已消费积分:"
						+ consumPoint + "\n剩余积分:" + lastPoint);
			}

			@Override
			public void onError(String errorInfo) {

			}
		});
	}

}
