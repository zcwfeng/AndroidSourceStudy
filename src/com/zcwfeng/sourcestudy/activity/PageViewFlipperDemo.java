package com.zcwfeng.sourcestudy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.zcwfeng.sourcestudy.R;

/**
 * Description:<br/>
 * Copyright (C), 2005-2015,David<br/>
 * Thies program is protected by copyright<br/>
 * David. Program name<br/>
 * Date:2015-5-7<br/>
 * 
 * @author David zcwfeng@126.com<br/>
 * @version 1.0
 * 
 */
public class PageViewFlipperDemo extends Activity implements
		android.view.GestureDetector.OnGestureListener {

	private int[] imgs = { R.drawable.img_1, R.drawable.img_2,
			R.drawable.img_3, R.drawable.img_4, R.drawable.img_5 };
	private int[] imgTips = { R.id.imageview1, R.id.imageview2,
			R.id.imageview3, R.id.imageview4, R.id.imageview5 };

	private GestureDetector gestureDetector = null;
	private ViewFlipper viewFlipper = null;

	private Activity mActivity = null;
	private int currentIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page_view_flipper);
		mActivity = this;
		viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
		gestureDetector = new GestureDetector(this); // 声明检测手势事件
		FrameLayout fragment;
		for (int i = 0; i < imgs.length; i++) { // 添加图片源
			fragment = new FrameLayout(this);
			ImageView iv = new ImageView(this);
			iv.setImageResource(imgs[i]);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			setImageBackground(0);

			viewFlipper.addView(iv, new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
		}

		viewFlipper.setAutoStart(true); // 设置自动播放功能（点击事件，前自动播放）
		viewFlipper.setFlipInterval(2000);
		if (viewFlipper.isAutoStart() && !viewFlipper.isFlipping()) {
			viewFlipper.startFlipping();
		}
	

	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		setImageBackground(viewFlipper.getDisplayedChild());

		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e2.getX() - e1.getX() > 120) { // 从左向右滑动（左进右出）
			currentIndex--;
			setImageBackground(currentIndex);

			Animation rInAnim = AnimationUtils.loadAnimation(mActivity,
					R.anim.push_right_in); // 向右滑动左侧进入的渐变效果（alpha 0.1 -> 1.0）
			Animation rOutAnim = AnimationUtils.loadAnimation(mActivity,
					R.anim.push_right_out); // 向右滑动右侧滑出的渐变效果（alpha 1.0 -> 0.1）

			viewFlipper.setInAnimation(rInAnim);
			viewFlipper.setOutAnimation(rOutAnim);
			viewFlipper.showPrevious();
			return true;
		} else if (e2.getX() - e1.getX() < -120) { // 从右向左滑动（右进左出）
			currentIndex++;

			setImageBackground(currentIndex);

			Animation lInAnim = AnimationUtils.loadAnimation(mActivity,
					R.anim.push_left_in); // 向左滑动左侧进入的渐变效果（alpha 0.1 -> 1.0）
			Animation lOutAnim = AnimationUtils.loadAnimation(mActivity,
					R.anim.push_left_out); // 向左滑动右侧滑出的渐变效果（alpha 1.0 -> 0.1）

			viewFlipper.setInAnimation(lInAnim);
			viewFlipper.setOutAnimation(lOutAnim);
			viewFlipper.showNext();
			return true;
		}
		return true;
	}

	private void setImageBackground(int selectItems) {
		for (int i = 0; i < imgTips.length; i++) {
			if (i == selectItems) {
				findViewById(imgTips[i])
						.setBackgroundResource(R.drawable.focus);

			} else {
				findViewById(imgTips[i]).setBackgroundResource(
						R.drawable.unfocus);
			}
			findViewById(imgTips[i]).invalidate();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		viewFlipper.stopFlipping(); // 点击事件后，停止自动播放
		viewFlipper.setAutoStart(false);
		return gestureDetector.onTouchEvent(event); // 注册手势事件
	}

}
