package com.zcwfeng.sourcestudy.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.R.id;
import com.zcwfeng.sourcestudy.R.layout;
import com.zcwfeng.sourcestudy.fragment.EnterTestAnimationFragment;

public class AnimationTestActivity extends BaseFragmentActivity implements
		OnClickListener {
	FragmentManager manager;
	EnterTestAnimationFragment mEnterTestAnimationFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_test);
		manager = getSupportFragmentManager();
		mEnterTestAnimationFragment = new EnterTestAnimationFragment();
		manager.beginTransaction()
				.add(R.id.fragment_containe, mEnterTestAnimationFragment)
				.commit();

	}

	public FragmentManager getMyFragManager() {
		return manager;
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if (manager.findFragmentByTag("currentFragment") instanceof EnterTestAnimationFragment) {
			finish();
		}
	}
}
