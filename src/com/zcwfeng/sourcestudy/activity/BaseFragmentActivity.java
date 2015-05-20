package com.zcwfeng.sourcestudy.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.zcwfeng.sourcestudy.R;

/**
 * Description:封装Activity<br/>
 * Copyright (C), 2005-2015,David<br/>
 * Thies program is protected by copyright<br/> David.
 * Program name<br/>
 * Date:${date}<br/>
 * @author David zcwfeng@126.com<br/>
 * @version 1.0
 *
 */
public class BaseFragmentActivity extends FragmentActivity implements OnClickListener {

	protected Fragment mCurrentFragment;
	public ImageView topBackBtn;

	/**
	 * 清楚 containerId 对应viewGroup里的所有fragment，替换为 制定的fragment
	 * 
	 * @param containerId
	 * @param fragment
	 *            要替换为的fragment
	 */
	public void replaceToFragment(int containerId, Fragment fragment) {
		if (fragment == null) {
			return;
		}
		try {
			if (fragment != mCurrentFragment) {
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				ft.replace(containerId, fragment);
				mCurrentFragment = fragment;
				ft.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 替换Fragment
	 * 
	 * @param fragment
	 */
	public void switchFragmentbyAdd(int containerId, Fragment fragment, String fragmentTag) {
		try {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
			if (currentFragment != null) {
				ft.hide(currentFragment).add(containerId, fragment, fragmentTag).addToBackStack(null).commit();
			} else {
				ft.add(containerId, fragment, fragmentTag).addToBackStack(null).commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 隐藏之前的frament，显示制定的fragment 注意 这里的fragment 必须是已经add过的
	 * 
	 * @param fragment
	 *            已经add过的fragment
	 */
	public void showFragment(Fragment fragment) {
		if (fragment == null) {
			return;
		}
		try {
			if (fragment != mCurrentFragment) {
				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				if (mCurrentFragment != null) {
					ft.hide(mCurrentFragment);
				}
				ft.show(fragment);
				android.app.FragmentTransaction ftapp;
				mCurrentFragment = fragment;
				ft.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置返回按钮的btn;
	 * 
	 * @param listener
	 */
	// public void setBackBtn(OnClickListener listener) {
	// topBackBtn = (ImageView) findViewById(R.id.topbar_leftimage);
	// if (listener != null) {
	// topBackBtn.setOnClickListener(listener);
	// } else {
	// topBackBtn.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // 返回
	// BaseFragmentActivity.super.onBackPressed();
	// }
	// });
	// }
	// }

	@Override
	public void onClick(View v) {

	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
}