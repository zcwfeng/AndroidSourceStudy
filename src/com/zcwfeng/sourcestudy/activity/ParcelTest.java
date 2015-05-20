package com.zcwfeng.sourcestudy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.dataset.User;

public class ParcelTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parcel_test);

		Intent intent = getIntent();
		Bundle bun = intent.getExtras();
		User user = bun.getParcelable("user");
		Log.e("zcw",user.toString());
	}
}
