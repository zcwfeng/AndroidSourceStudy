package com.zcwfeng.sourcestudy.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Description:<br/>
 * Copyright (C), 2005-2015,David<br/>
 * Thies program is protected by copyright<br/>
 * David. Program name<br/>
 * Date:2015-5-6<br/>
 * 
 * @author David zcwfeng@126.com<br/>
 * @version 1.0
 * 
 */
public class MyIntentService extends IntentService {
	public MyIntentService() {
		super("");
	}

	public MyIntentService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// 经测试，IntentService里面是可以进行耗时的操作的
		// IntentService使用队列的方式将请求的Intent加入队列，然后开启一个worker
		// thread(线程)来处理队列中的Intent
		// 对于异步的startService请求，IntentService会处理完成一个之后再处理第二个
		Log.d("zcw", "onStart-IntentService");
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Log.d("zcw", "睡眠结束");

	}

}
