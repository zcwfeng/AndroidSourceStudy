package com.zcwfeng.sourcestudy.activity;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.R.id;
import com.zcwfeng.sourcestudy.R.layout;
import com.zcwfeng.sourcestudy.service.IStockQuoteService;
import com.zcwfeng.sourcestudy.service.MyService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AIDLTestActivity extends Activity {

	static final String TAG = "MYyActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aidltest);

		Button btnCall = (Button) findViewById(R.id.btnCall);
		if (btnCall != null)
			btnCall.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// 绑定一个服务
					bindMyService();
				}
			});
	}

	IStockQuoteService iService = null;
	private ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// 返回AIDL接口对象，然后可以调用AIDL方法
			iService = IStockQuoteService.Stub.asInterface(service);
			double value = 0.0;
			try {
				value = iService.getPrice("");
			} catch (RemoteException e) {
				Log.e(TAG, "调用出错！");
				e.printStackTrace();
			}
			Log.e(TAG, "返回数值为：" + value);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(TAG, "释放Service");
		}
	};

	private void bindMyService() {
		// Intent intent=new Intent("com.dongzi.IStockQuoteService");
		Intent intent = new Intent(this, MyService.class);
		startService(intent);

		bindService(intent, conn, Context.BIND_AUTO_CREATE);
	}
}
