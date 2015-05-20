package com.zcwfeng.sourcestudy.activity;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.R.layout;
import com.zcwfeng.sourcestudy.service.LocalService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;

public class ServiceTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_test);
		BinderService();//xml 静态和他对应 动态绑定

//		startCustomService();
	}

	private void startCustomService() {
		Intent intent = new Intent(this, LocalService.class);
		startService(intent);
	}

	// ====================
	LocalService localService = null;

	// 用bindService方法启动服务
	public void BinderService() {
		Intent intent = new Intent(this, LocalService.class);
		bindService(intent, new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName componentName,
					IBinder binder) {
				// 调用bindService方法启动服务时候，如果服务需要与activity交互，
				// 则通过onBind方法返回IBinder并返回当前本地服务
				localService = ((LocalService.LocalBinder) binder).getService();
				// 这里可以提示用户,或者调用服务的某些方法
			}

			@Override
			public void onServiceDisconnected(ComponentName componentName) {
				localService = null;
				// 这里可以提示用户
			}
		}, Context.BIND_AUTO_CREATE);
	}

}
