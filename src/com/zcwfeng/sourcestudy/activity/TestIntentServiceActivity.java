package com.zcwfeng.sourcestudy.activity;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.R.layout;
import com.zcwfeng.sourcestudy.service.MyIntentService;
import com.zcwfeng.sourcestudy.service.MyService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TestIntentServiceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_intent_service);

		startService(new Intent(this, MyService.class));// 主界面阻塞，最终会出现Application
														// not responding
		// 连续两次启动IntentService，会发现应用程序不会阻塞，而且最重的是第二次的请求会再第一个请求结束之后运行(这个证实了IntentService采用单独的线程每次只从队列中拿出一个请求进行处理)
		startService(new Intent(this, MyIntentService.class));
		startService(new Intent(this, MyIntentService.class));
	}
}
