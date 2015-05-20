package com.zcwfeng.sourcestudy;

import java.io.IOException;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
	private static MyApplication mApp;

	@Override
	public void onCreate() {
		mApp = this;
		// initCrashHandler();
		Log.e("vm", System.getProperty("java.vm.name"));
	}

	/**
	 * 错误日志处理
	 */
	// private void initCrashHandler()
	// {
	// CrashHandler catchHandler = CrashHandler.getInstance();
	// catchHandler.init(getApplicationContext());
	// }

	/**
	 * 获取ȡapplication context
	 * 
	 * @return
	 */
	public static MyApplication getApplication() {
		return mApp;
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
	}

}
