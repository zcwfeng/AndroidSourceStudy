package com.zcwfeng.sourcestudy.fragment;

import java.util.Random;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.nineoldandroids.view.animation.AnimatorProxy;
import com.zcwfeng.sourcestudy.R;

/**
 * Description:利用android3.0库，写的一个property 动画<br/>
 * Copyright (C), 2005-2015,David<br/>
 * Thies program is protected by copyright<br/>
 * David. Program name<br/>
 * Date:2015-5-8<br/>
 * 
 * @author David zcwfeng@126.com<br/>
 * @version 1.0
 * 
 */
public class TestViewAnimationFragmentProperty extends AbstractFragment
		implements AnimatorListener {
	private View mRootView;
	private FrameLayout mContainer;
	private static final int[] PHOTOS = new int[] { R.drawable.img_1,
			R.drawable.img_2, R.drawable.img_3, R.drawable.img_4,
			R.drawable.img_5 };
	private ImageView mView;
	private Random mRandom = new Random();
	private int mIndex = 0;
	private static final int ANIM_COUNT = 4;
	AnimatorSet anim;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater
				.inflate(R.layout.enter_test_animation_property_layout,
						container, false);
		
		mContainer = (FrameLayout) mRootView.findViewById(R.id.manim_container);
		if(this.isAdded()) {
			mView = createNewView();
			mContainer.addView(mView);
		}else {
			Log.e("zcw", "getActivity() == null");
		}
		
		return mRootView;
	}

	private ImageView createNewView() {
		
		ImageView ret = new ImageView(getActivity());
		ret.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		ret.setScaleType(ScaleType.FIT_XY);
		ret.setImageResource(PHOTOS[mIndex]);
		mIndex = (mIndex + 1 < PHOTOS.length) ? mIndex + 1 : 0;

		return ret;
	}

	private void nextAnimation() {
		anim = new AnimatorSet();
		final int index = mRandom.nextInt(ANIM_COUNT);

		switch (index) {
		case 0:
			anim.playTogether(
					ObjectAnimator.ofFloat(mView, "scaleX", 1.5f, 1f),
					ObjectAnimator.ofFloat(mView, "scaleY", 1.5f, 1f));
			break;

		case 1:
			anim.playTogether(ObjectAnimator.ofFloat(mView, "scaleX", 1, 1.5f),
					ObjectAnimator.ofFloat(mView, "scaleY", 1, 1.5f));
			break;

		case 2:
			AnimatorProxy.wrap(mView).setScaleX(1.5f);
			AnimatorProxy.wrap(mView).setScaleY(1.5f);
			anim.playTogether(ObjectAnimator.ofFloat(mView, "translationY",
					80f, 0f));
			break;

		case 3:
		default:
			AnimatorProxy.wrap(mView).setScaleX(1.5f);
			AnimatorProxy.wrap(mView).setScaleY(1.5f);
			anim.playTogether(ObjectAnimator.ofFloat(mView, "translationX", 0f,
					40f));
			break;
		}

		anim.setDuration(3000);
		anim.addListener(this);
		anim.start();
	}

	@Override
	public void onResume() {
		super.onResume();
		nextAnimation();
	}

	@Override
	public void onAnimationStart(Animator animation) {

	}

	@Override
	public void onAnimationEnd(Animator animation) {
		mContainer.removeView(mView);
		mView = createNewView();
		mContainer.addView(mView);
		nextAnimation();
	}

	@Override
	public void onAnimationCancel(Animator animation) {

	}

	@Override
	public void onAnimationRepeat(Animator animation) {

	}

	@Override
	public void onStop() {
		super.onStop();
		anim.removeAllListeners();
		anim.cancel();
	}
}
