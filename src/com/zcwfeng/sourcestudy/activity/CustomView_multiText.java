package com.zcwfeng.sourcestudy.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.R.id;
import com.zcwfeng.sourcestudy.R.layout;
import com.zcwfeng.sourcestudy.views.waterflow.MultipleTextView;
import com.zcwfeng.sourcestudy.views.waterflow.MultipleTextView.OnMultipleTVItemClickListener;

public class CustomView_multiText extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_view_multi_text);
		List<String> dataList = new ArrayList<String>();

		dataList.add("Mason Liu");
		dataList.add("Mason Liu");

		dataList.add("天盟天盟天盟天盟");
		dataList.add("Mason Mason Mason");

		dataList.add("Mason Liu");
		dataList.add("天盟");
		dataList.add("天盟天盟天盟");
		dataList.add("Mason Mason");

		dataList.add("Mason");
		dataList.add("天");
		dataList.add("天");
		dataList.add("Ma");
		dataList.add("Maaliasdgjkasfjdasjfdljafasf;j");
		MultipleTextView rl = (MultipleTextView) findViewById(R.id.main_rl);
		rl.setOnMultipleTVItemClickListener(new OnMultipleTVItemClickListener() {

			@Override
			public void onMultipleTVItemClick(View view, int position) {

			}
		});
		rl.setTextViews(dataList);
	}
}
