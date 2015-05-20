package com.zcwfeng.sourcestudy.activity;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TestBroadcastActivity extends Activity {
	final String INTERNAL_ACTION_2 = "com.zcwfeng.sourcestudy.Internal_2";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_broadcast);

		Intent intent = new Intent(INTERNAL_ACTION_2);
		intent.putExtra("Name", "hellogv");
		intent.putExtra("Blog", "http://blog.csdn.net/hellogv");
		sendBroadcast(intent);// 传递过去
	}
}
  