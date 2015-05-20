package com.zcwfeng.sourcestudy.views.gestureview;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.zcwfeng.sourcestudy.R;

/**
 * Description:<br/>
 * Copyright (C), 2005-2015,David<br/>
 * Thies program is protected by copyright<br/>
 * David. Program name<br/>
 * Date:2015-5-13<br/>
 * 
 * @author David zcwfeng@126.com<br/>
 * @version 1.0
 * 
 */
public class FragmentLayoutImageView extends FrameLayout {

	ImageTouchView mImageView;
	SeekBar mSeekBar;
	LinearLayout mSeekBarLayout;

	public FragmentLayoutImageView(Context context) {
		super(context);
		init(context);
	}

	public FragmentLayoutImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mImageView = new ImageTouchView(context);
		LayoutParams mTouchImgParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mImageView.setLayoutParams(mTouchImgParams);
		mImageView.setImageResource(R.drawable.ic_launcher);
		mImageView.setScaleType(ScaleType.MATRIX);
		this.addView(mImageView);
		
		
		mSeekBarLayout = (LinearLayout) View.inflate(getContext(),
				R.layout.touchimg_controll_seekbar, null);
		mSeekBarLayout.setGravity(Gravity.BOTTOM);
		mSeekBar = (SeekBar) mSeekBarLayout.findViewById(R.id.seekbar_cotroll);
		mSeekBar.setMax(5);
		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.e("seek-bar", seekBar.getProgress() + "");
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				getCurrentImageView(seekBar.getProgress());
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					Toast.makeText(getContext(),
							String.valueOf(seekBar.getProgress()) + fromUser,
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		this.addView(mSeekBarLayout);
	}

	public void getCurrentImageView(int i) {
		AssetManager assetManager = getContext().getAssets();
		try {
			InputStream is = assetManager.open("imgtest/guanlangaoshou_" + i
					+ ".jpg");
			Bitmap mBitmap = BitmapFactory.decodeStream(is);
			BitmapDrawable bd = new BitmapDrawable(getResources(), mBitmap);
			mImageView.setImageDrawable(bd);
			mImageView.invalidate();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	@Override
	protected void onDisplayHint(int hint) {
		super.onDisplayHint(hint);
	}

	@Override
	protected void onAnimationEnd() {
		// TODO Auto-generated method stub
		super.onAnimationEnd();
	}

	@Override
	protected void onAnimationStart() {
		// TODO Auto-generated method stub
		super.onAnimationStart();
	}

	@Override
	public void onCancelPendingInputEvents() {
		// TODO Auto-generated method stub
		super.onCancelPendingInputEvents();
	}

	@Override
	public boolean onDragEvent(DragEvent event) {
		return super.onDragEvent(event);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
	}

}
