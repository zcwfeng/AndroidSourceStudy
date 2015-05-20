package com.zcwfeng.sourcestudy.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Description: <br/>
 * Copyright (C), 2005-2015,David<br/>
 * Thies program is protected by copyright<br/>
 * David. Program name<br/>
 * Date:2015-5-5<br/>
 * 
 * @author David zcwfeng@126.com<br/>
 * @version 1.0
 * 
 */
public class TestBroadcastReceiver extends BroadcastReceiver {
	/*
	 * 接收静态注册广播的BroadcastReceiver， step1:要到AndroidManifest.xml这里注册消息 <receiver
	 * android:name="TestBroadcastReceiver"> <intent-filter> <action
	 * android:name="com.testBroadcastReceiver.Internal_2"/> </intent-filter>
	 * </receiver> step2:定义消息的字符串 step3:通过Intent传递消息来驱使BroadcastReceiver触发
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Toast.makeText(context, "静态:" + action, 1000).show();
	}

	// 2.动态注册BroadcastReceiver:
	// IntentFilter intentFilter = new IntentFilter();
	//
	// 　　intentFilter.addAction(String); //为BroadcastReceiver指定action，即要监听的消息名字。
	//
	// registerReceiver(MyBroadcastReceiver,intentFilter); //注册监听
	//
	// unregisterReceiver(MyBroadcastReceiver); //取消监听
	//
	// 　　//(一般：在onStart中注册，onStop中取消unregisterReceiver)
	//
	// private class MyBroadcastReceive extends BroadcastReceiver
	//
	// {
	//
	// public void onReceive(Context context, Intent intent)
	//
	// {
	//
	// String action = intent.getAction();
	//
	// if(intent.ACTION_BATTERY_CHANGED.equals(action)) //判断是否接到电池变换消息
	//
	// {
	//
	// //处理内容
	//
	// }
	//
	// }
	//
	// }
}
