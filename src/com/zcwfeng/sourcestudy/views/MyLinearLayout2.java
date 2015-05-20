package com.zcwfeng.sourcestudy.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout2 extends LinearLayout {
	private static final String TAG = "Touch";

	public MyLinearLayout2(Context context) {
		super(context);
	}

	public MyLinearLayout2(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@SuppressLint("NewApi")
	public MyLinearLayout2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		Log.i(TAG, "onTouchEvent MyLinearLayout2 " + ev.getAction() + ": "
				+ super.onTouchEvent(ev));
		return super.onTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.i(TAG, "onInterceptTouchEvent MyLinearLayout2 " + ev.getAction()
				+ ": " + super.onInterceptTouchEvent(ev));
		return super.onInterceptTouchEvent(ev);
		// return true;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.i(TAG, "linearlayout2 dispatchTouchEvent()");
		 return super.dispatchTouchEvent(ev);
//		return true;
	}
}
