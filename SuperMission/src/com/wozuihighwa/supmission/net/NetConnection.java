package com.wozuihighwa.supmission.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

import com.wozuihighwa.supmission.utils.Config;

/**
 * 网络连接类
 * 
 * @author wozuihighwa
 * @date 2015-1-22
 */
public class NetConnection {
	/**
	 * 网络连接方法
	 * 
	 * @param url
	 * @param method
	 * @param successCallBack
	 * @param failCallBack
	 * @param kvs
	 */
	public NetConnection(final String url, final String method,
			final SuccessCallBack successCallBack,
			final FailCallBack failCallBack, final String... kvs) {
		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				StringBuilder paramsStr = new StringBuilder();
				for (int i = 0; i < kvs.length; i += 2) {
					paramsStr.append(kvs[i]).append("=").append(kvs[i + 1]);
					if (i != kvs.length - 2) {
						paramsStr.append("&");
					}
				}

				try {
					HttpURLConnection conn;
					if (method == Config.HTTP_POST) {
						conn = (HttpURLConnection) new URL(url)
								.openConnection();

						conn.setRequestMethod("POST");
						conn.setDoInput(true);
						conn.setDoOutput(true);

						BufferedWriter bw = new BufferedWriter(
								new OutputStreamWriter(conn.getOutputStream()));
						Log.i("requestData",
								"---requestData---" + paramsStr.toString());
						bw.write(paramsStr.toString());
						bw.flush();
						bw.close();

						BufferedReader br = new BufferedReader(
								new InputStreamReader(conn.getInputStream(),
										Config.CHARSET_UTF_8));
						String line = null;
						StringBuffer result = new StringBuffer();
						while ((line = br.readLine()) != null) {
							result.append(line);
						}
						Log.i("responseData",
								"---responseData---" + result.toString());
						br.close();
						conn.disconnect();
						return result.toString();

					} else if (method == Config.HTTP_GET) {
						conn = (HttpURLConnection) new URL(url + "?"
								+ paramsStr.toString()).openConnection();
					}

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				return null;
			}

			protected void onPostExecute(String result) {
				if (result != null) {
					if (successCallBack != null) {
						successCallBack.onSuccess(result);
					}
				} else {
					if (failCallBack != null) {
						failCallBack.onFail("服务器无响应！");
					}
				}
				super.onPostExecute(result);
			}
		}.execute();
	}

	/**
	 * 连接成功的回调方法
	 */
	public static interface SuccessCallBack {
		void onSuccess(String result);
	}

	/**
	 * 连接失败的回调方法
	 */
	public static interface FailCallBack {
		void onFail(String errorInfo);
	}
}
