package com.zcwfeng.sourcestudy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zcwfeng.sourcestudy.R;

public class TestIntentShowActivity extends Activity {

	private TextView mTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_intent_show);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String mString = bundle.getString("data");
		
		
		mTextView = (TextView) findViewById(R.id.content_intent);
		mTextView.setText(mString);
	}
}
