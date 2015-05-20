package com.zcwfeng.sourcestudy.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class MyView extends TextView {
	private static final String TAG = "Touch";

	public MyView(Context context) {
		super(context);
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		Log.i(TAG,
				"onTouchEvent  MyView " + ev.getAction() + ": "
						+ super.onTouchEvent(ev));
		return super.onTouchEvent(ev);
		// return true;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.i(TAG, "MyView  dispatchTouchEvent()");
		return super.dispatchTouchEvent(ev);
	}
}
