package com.zcwfeng.sourcestudy.pref;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
/**
 * 代表另外一个应用（B)
 * 
 * B启动修改并保存sharepreference
 * @author david
 *
 */
public class SimplePreferenceActivity extends Activity {
	private EditText nameText;
	private EditText ageText;
	private EditText heightText;
	public static final String PREFER_NAME = "SaveSet";
	public static int MODE = MODE_WORLD_WRITEABLE + MODE_MULTI_PROCESS+ MODE_WORLD_READABLE;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		//
		// nameText = (EditText)findViewById(R.id.name);
		// ageText = (EditText)findViewById(R.id.age);
		// heightText = (EditText)findViewById(R.id.height);
    }

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadSharedPreferences();
	}

	private void loadSharedPreferences() {
		// TODO Auto-generated method stub
		SharedPreferences share = getSharedPreferences(PREFER_NAME, MODE);
		String name = share.getString("Name", "Tom");
		int age = share.getInt("Age", 20);
		float height = share.getFloat("Height", 1.81f);
		
		nameText.setText(name);
  	    ageText.setText(String.valueOf(age));
  	    heightText.setText(String.valueOf(height));
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		saveSharedPreferences();
	}

	private void saveSharedPreferences() {
		// TODO Auto-generated method stub
		SharedPreferences share = getSharedPreferences(PREFER_NAME, MODE);
		SharedPreferences.Editor editor = share.edit();
		
		editor.putString("Name", nameText.getText().toString());
		editor.putInt("Age", Integer.parseInt(ageText.getText().toString()));
  	    editor.putFloat("Height", Float.parseFloat(heightText.getText().toString()));
  	    editor.commit();		
	}    
}