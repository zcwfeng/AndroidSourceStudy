package com.zcwfeng.sourcestudy;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zcwfeng.sourcestudy.dataset.User;

public class MainActivity extends ListActivity {
	final String TAG = "zcw";

	public native void uninstall(String packageDir, int sdkVersion);

	// 静态代码块加载C语言库文件
	static {
		System.loadLibrary("AndroidSourceStudy");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.e("TAG", "onStart");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.e("TAG", "onSaveInstanceState");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.e("TAG", "onStart");
		List<String> mList = fillArray();
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, mList);
		setListAdapter(adapter);

		uninstallCallbackC();

		if (BuildConfig.DEBUG) {
			Log.d("debug", "test builddebug");
		}

		if (Intent.ACTION_VIEW.equals(getIntent().getAction())) {
			Intent intent = getIntent();
			String scheme = intent.getScheme();
			Uri uri = intent.getData();

			Log.e(TAG, "scheme: " + scheme);
			Log.e(TAG, "path: " + uri.getPath());
			Log.e(TAG, "Host: " + uri.getHost());
			Log.e(TAG, "name: " + uri.getQueryParameter("name"));
			Log.e(TAG, "age: " + uri.getQueryParameter("age"));
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.e("TAG", "onPause");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.e("TAG", "onRestart");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.e("TAG", "onDestroy");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.e("TAG", "onStop");

	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.e("TAG", "onResume");

	}

	private void uninstallCallbackC() {
		String packageDir = "/data/data/" + getPackageName();
		int sdkVersion = android.os.Build.VERSION.SDK_INT;
		uninstall(packageDir, sdkVersion);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = null;
		switch (position) {

		case 0:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.HandlerBasicActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.AdapterTestActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent();
			intent.setClass(
					MainActivity.this,
					com.zcwfeng.sourcestudy.activity.AnimationTestActivity.class);
			startActivity(intent);
			break;
		case 3:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.ServiceTestActivity.class);
			startActivity(intent);
			break;
		case 4:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.AIDLTestActivity.class);
			startActivity(intent);
			break;
		case 5:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.StorageTestActivity.class);
			startActivity(intent);
			break;
		case 6:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.ViewEventActivity.class);
			startActivity(intent);
			break;
		case 7:
			intent = new Intent();
			intent.setClass(
					MainActivity.this,
					com.zcwfeng.sourcestudy.activity.TestBroadcastActivity.class);
			startActivity(intent);
			break;
		case 8:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.ContentProviderDemo.class);
			startActivity(intent);
			break;
		case 9:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.MyContentDemo.class);
			startActivity(intent);
			break;
		case 10:
			intent = new Intent();
			intent.setClass(
					MainActivity.this,
					com.zcwfeng.sourcestudy.activity.TestIntentServiceActivity.class);
			startActivity(intent);
			break;
		case 11:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.ImageDetailsActivity.class);
			startActivity(intent);
			break;
		case 12:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.CustomView_multiText.class);
			startActivity(intent);
			break;
		case 13:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.PageViewFlipperDemo.class);
			startActivity(intent);
			break;
		case 14:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.TestListVewActivity.class);
			startActivity(intent);
			break;
		case 15:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.thirdparty.volley.TestVolley.class);
			startActivity(intent);
			break;
		case 16:
			intent = new Intent();
			intent.setClass(
					MainActivity.this,
					com.zcwfeng.sourcestudy.thirdparty.eventbus.TestEventBusMainActivity.class);
			startActivity(intent);
			break;
		case 17:
			intent = new Intent();
			intent.setClass(
					MainActivity.this,
					com.zcwfeng.sourcestudy.thirdparty.greendao.NoteActivity.class);
			startActivity(intent);
			break;
		case 18:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.download.DownloadActivity.class);
			startActivity(intent);
			break;
		case 19:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.GestureScalActivity.class);
			startActivity(intent);
			break;

		case 20:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.SqliteOperateTest.class);
			startActivity(intent);
			break;

		case 21:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.ParcelTest.class);
			User user = new User();
			user.setAge(11);
			user.setName("zcwfeng");
			Bundle bundle = new Bundle();
			bundle.putParcelable("user", user);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case 22:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.DownLoadActivity.class);

			startActivity(intent);
			break;
		case 23:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.activity.TestIntentActivity.class);

			startActivity(intent);
			break;
		case 24:
			intent = new Intent();
			intent.setClass(MainActivity.this,
					com.zcwfeng.sourcestudy.media.VideoSurfaceDemo.class);

			startActivity(intent);
			break;
		default:
			break;
		}
	}

	private List<String> fillArray() {
		List<String> list = new ArrayList<>();
		list.add("Handler Basic");
		list.add("Adapters");
		list.add("Animation Basic");
		list.add("service activity");
		list.add("AIDL模拟不同APK共享进程通信");
		list.add("存储");
		list.add("event 机制验证");
		list.add("BroadCast Basic");
		list.add("ContentProvider Basic");
		list.add("MyContentDemo ContentProvidre完整例子");
		list.add("IntentService Basic");
		list.add("WaterFlow Demo");
		list.add("CustomView_multiText");
		list.add("PageViewFlipperDemo");
		list.add("listview 特殊方法");
		list.add("volley_test 使用");
		list.add("eventbus_test 使用");
		list.add("greendao_test 使用");
		list.add("multithread download");
		list.add("Gesture Matrix Imageview");
		list.add("sqlite_test");
		list.add("parcel_test");
		list.add("multithead download 2");
		list.add("intent 细节");
		list.add("SurfaceView Media播放");

		return list;
	}

}
