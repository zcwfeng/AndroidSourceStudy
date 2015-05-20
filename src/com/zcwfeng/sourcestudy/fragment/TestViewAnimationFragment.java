package com.zcwfeng.sourcestudy.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.zcwfeng.sourcestudy.R;

/**
 * Description:<br/>
 * Copyright (C), 2005-2015,David<br/>
 * Thies program is protected by copyright<br/>
 * David. Program name<br/>
 * Date:2015-5-3<br/>
 * 
 * @author David zcwfeng@126.com<br/>
 * @version 1.0
 * 
 */
public class TestViewAnimationFragment extends AbstractFragment implements
		OnClickListener {

	View mRootView;
	private Button rotateButton = null;
	private Button scaleButton = null;
	private Button alphaButton = null;
	private Button translateButton = null;
	private Button doubleAniButton = null;
	private Button frameAniButton = null;
	private ImageView image = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.view_animation_layout, container,
				false);

		rotateButton = (Button) mRootView.findViewById(R.id.rotateButton);
		scaleButton = (Button) mRootView.findViewById(R.id.scaleButton);
		alphaButton = (Button) mRootView.findViewById(R.id.alphaButton);
		translateButton = (Button) mRootView.findViewById(R.id.translateButton);
		doubleAniButton = (Button) mRootView.findViewById(R.id.doubleAniSetBtn);
		frameAniButton = (Button) mRootView.findViewById(R.id.frame_anim_btn);
		image = (ImageView) mRootView.findViewById(R.id.image);
		rotateButton.setOnClickListener(new RotateButtonListener());
		scaleButton.setOnClickListener(new ScaleButtonListener());
		alphaButton.setOnClickListener(new AlphaButtonListener());
		translateButton.setOnClickListener(new TranslateButtonListener());
		doubleAniButton.setOnClickListener(new DoubleButtonListener());
		frameAniButton.setOnClickListener(new FrameAniButtonListener());
		return mRootView;
	}

	@Override
	public void onClick(View v) {

	}

	// --------------------------------------------------------------------------------

	// class AlphaButtonListener implements OnClickListener {
	//
	// public void onClick(View v) {
	//
	// // 创建一个AnimationSet对象，参数为Boolean型，
	//
	// // true表示使用Animation的interpolator，false则是使用自己的
	//
	// AnimationSet animationSet = new AnimationSet(true);
	//
	// // 创建一个AlphaAnimation对象，参数从完全的透明度，到完全的不透明
	//
	// AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
	//
	// // 设置动画执行的时间
	//
	// alphaAnimation.setDuration(500);
	//
	// // 将alphaAnimation对象添加到AnimationSet当中
	//
	// animationSet.addAnimation(alphaAnimation);
	//
	// // 使用ImageView的startAnimation方法执行动画
	//
	// image.startAnimation(animationSet);
	//
	// }
	//
	// }
	//
	// class RotateButtonListener implements OnClickListener {
	//
	// public void onClick(View v) {
	//
	// AnimationSet animationSet = new AnimationSet(true);
	//
	// // 参数1：从哪个旋转角度开始
	//
	// // 参数2：转到什么角度
	//
	// // 后4个参数用于设置围绕着旋转的圆的圆心在哪里
	//
	// //
	// 参数3：确定x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
	//
	// // 参数4：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
	//
	// // 参数5：确定y轴坐标的类型
	//
	// // 参数6：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
	//
	// RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
	//
	// Animation.RELATIVE_TO_SELF, 0.5f,
	//
	// Animation.RELATIVE_TO_SELF, 0.5f);
	//
	// rotateAnimation.setDuration(1000);
	//
	// animationSet.addAnimation(rotateAnimation);
	//
	// image.startAnimation(animationSet);
	//
	// }
	//
	// }
	//
	// class ScaleButtonListener implements OnClickListener {
	//
	// public void onClick(View v) {
	//
	// AnimationSet animationSet = new AnimationSet(true);
	//
	// // 参数1：x轴的初始值
	//
	// // 参数2：x轴收缩后的值
	//
	// // 参数3：y轴的初始值
	//
	// // 参数4：y轴收缩后的值
	//
	// // 参数5：确定x轴坐标的类型
	//
	// // 参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
	//
	// // 参数7：确定y轴坐标的类型
	//
	// // 参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
	//
	// ScaleAnimation scaleAnimation = new ScaleAnimation(
	//
	// 0, 0.1f, 0, 0.1f,
	//
	// Animation.RELATIVE_TO_SELF, 0.5f,
	//
	// Animation.RELATIVE_TO_SELF, 0.5f);
	//
	// scaleAnimation.setDuration(1000);
	//
	// animationSet.addAnimation(scaleAnimation);
	//
	// image.startAnimation(animationSet);
	//
	// }
	//
	// }
	//
	// class TranslateButtonListener implements OnClickListener {
	//
	// public void onClick(final View v) {
	//
	// AnimationSet animationSet = new AnimationSet(true);
	//
	// // 参数1～2：x轴的开始位置
	//
	// // 参数3～4：y轴的开始位置
	//
	// // 参数5～6：x轴的结束位置
	//
	// // 参数7～8：x轴的结束位置
	//
	// TranslateAnimation translateAnimation =
	//
	// new TranslateAnimation(
	//
	// Animation.RELATIVE_TO_SELF, 0f,
	//
	// Animation.RELATIVE_TO_SELF, 0.5f,
	//
	// Animation.RELATIVE_TO_SELF, 0f,
	//
	// Animation.RELATIVE_TO_SELF, 0.5f);
	//
	// translateAnimation.setDuration(1000);
	//
	// animationSet.addAnimation(translateAnimation);
	//
	// image.startAnimation(animationSet);
	//
	// animationSet.setAnimationListener(new AnimationListener() {
	//
	// @Override
	// public void onAnimationStart(Animation animation) {
	// Log.e("zcw", "start:" + v.getX() + v.getY());
	// }
	//
	// @Override
	// public void onAnimationRepeat(Animation animation) {
	// Log.e("zcw", "Repeat:" + v.getX() + v.getY());
	//
	// }
	//
	// @Override
	// public void onAnimationEnd(Animation animation) {
	// Log.e("zcw", "end:" + v.getX() + v.getY());
	//
	// }
	// });
	//
	// }
	//
	// }

	class AlphaButtonListener implements OnClickListener {
		public void onClick(View v) {
			// 使用AnimationUtils装载动画配置文件
			Animation animation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.alpha);
			// 启动动画
			image.startAnimation(animation);
		}

	}

	class RotateButtonListener implements OnClickListener {
		public void onClick(View v) {
			Animation animation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.rotate);
			image.startAnimation(animation);
		}

	}

	class ScaleButtonListener implements OnClickListener {
		public void onClick(View v) {
			Animation animation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.scale);
			image.startAnimation(animation);
		}
	}

	class TranslateButtonListener implements OnClickListener {
		public void onClick(View v) {
			Animation animation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.translate);
			image.startAnimation(animation);

		}

	}

	// --------------------------------------------------------------------------------
	/*
	 * AnimationSet的具体使用方法 1.AnimationSet是Animation的子类；
	 * 2.一个AnimationSet包含了一系列的Animation；
	 * 3.针对AnimationSet设置一些Animation的常见属性（如startOffset
	 * ，duration等），可以被包含在AnimationSet当中的Animation集成；
	 * 例：一个AnimationSet中有两个Animation，效果叠加
	 */

	// class DoubleButtonListener implements OnClickListener {
	// public void onClick(View v) {
	// // 使用AnimationUtils装载动画配置文件
	// Animation animation = AnimationUtils.loadAnimation(getActivity(),
	// R.anim.doubleani);
	// // 启动动画
	// image.startAnimation(animation);
	// }
	//
	// }

	class DoubleButtonListener implements OnClickListener {
		public void onClick(View v) {
			AnimationSet animationSet = new AnimationSet(true);
			AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
			RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			rotateAnimation.setDuration(1000);
			animationSet.addAnimation(rotateAnimation);
			animationSet.addAnimation(alphaAnimation);
			image.startAnimation(animationSet);
		}
	}

	// -------------------------------------------------------------------------
	// Frame Animation
	class FrameAniButtonListener implements OnClickListener {
		public void onClick(View v) {
			image.setBackgroundResource(R.anim.frame_ani);
			AnimationDrawable animationDrawable = (AnimationDrawable) image
					.getBackground();
			animationDrawable.start();

		}

	}

}
