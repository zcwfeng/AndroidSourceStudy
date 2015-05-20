package com.zcwfeng.sourcestudy.pref;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author 代表了一个应用（A）
 * 
 *         A 读取B中保存的sharepreference
 * 
 */
public class SharePreferenceActivity extends Activity {
	public static final String PREFERENCE_PACKAGE = "edu.cczu.SimplePreference";
	public static final String PREFERENCE_NAME = "SaveSet";
	public static int MODE = Context.MODE_WORLD_READABLE
			+ Context.MODE_WORLD_WRITEABLE + Context.MODE_MULTI_PROCESS;
	private TextView labelView;
	private static String TAG = "LIFECYCLE";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		Context c = null;
		// labelView = (TextView)findViewById(R.id.label);

		try {
			c = this.createPackageContext(PREFERENCE_PACKAGE,
					Context.CONTEXT_IGNORE_SECURITY);

		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SharedPreferences sharedPreferences = c.getSharedPreferences(
				PREFERENCE_NAME, MODE);

		String name = sharedPreferences.getString("Name", "Tom");
		int age = sharedPreferences.getInt("Age", 20);
		float height = sharedPreferences.getFloat("Height", 1.81f);

		String msg = "";
		msg += "姓名：" + name + "\n";
		msg += "年龄：" + String.valueOf(age) + "\n";
		msg += "身高：" + String.valueOf(height) + "\n";	
		
		labelView.setText(msg);   
		
		Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
		
        Log.i(TAG, "(1) onCreate()");
        
		// //重新设置数据----start
		// // SharedPreferences share = getSharedPreferences(PREFER_NAME, MODE);
		// SharedPreferences.Editor editor = sharedPreferences.edit();
		// //
		// editor.putString("Name", "哈哈");
		// editor.putInt("Age", 121);
		// editor.putFloat("Height", 2.2f);
		// editor.commit();
		// //设置数据结束----end
    }

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "(2) onStart()");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "(8) onStop()");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "(9) onDestroy()");
		//System.exit(0);
	}    
}