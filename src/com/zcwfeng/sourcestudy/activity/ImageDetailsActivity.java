package com.zcwfeng.sourcestudy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.views.waterflow.TravelsFragment;

public class ImageDetailsActivity extends FragmentActivity {

	FragmentManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_details);
		manager = getSupportFragmentManager();
		manager.beginTransaction().add(R.id.fragment_container_waterflow,
				new TravelsFragment()).commit();

	}
}
