package com.zcwfeng.sourcestudy.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.SpinnerAdapter;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.R.id;
import com.zcwfeng.sourcestudy.R.layout;
import com.zcwfeng.sourcestudy.fragment.SimpleCursorAdapterFragment;
 
/**
 * Description:Adapter 测试<br/>
 * Copyright (C), 2005-2015,David<br/>
 * Thies program is protected by copyright<br/>
 * David. Program name<br/>
 * Date:${date}<br/>
 * 
 * @author David zcwfeng@126.com<br/>
 * @version 1.0
 * 
 */
public class AdapterTestActivity extends BaseFragmentActivity implements
		OnClickListener {
	BaseAdapter mBaseA;
	ListAdapter mListA;
	SpinnerAdapter mSpinnerAdapter;

	SimpleAdapter mSimpleAdapter;
	ArrayAdapter mArrayAdapter;

	SimpleCursorAdapter mSimpleCursorAdapter;

	FragmentManager manager;
	SimpleCursorAdapterFragment mSimpleCursorAFg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adapter_test);
		manager = getSupportFragmentManager();
		gotoPage(0);
	}
	

	private void gotoPage(int type) {
		switch (type) {
		case 1:
			if (mSimpleCursorAFg == null)
				mSimpleCursorAFg = new SimpleCursorAdapterFragment();
			manager.beginTransaction()
					.add(R.id.homepage_dynymic_containner, mSimpleCursorAFg)
					.commit();

			break;

		default:
			if (mSimpleCursorAFg == null)
				mSimpleCursorAFg = new SimpleCursorAdapterFragment();
			manager.beginTransaction()
					.add(R.id.homepage_dynymic_containner, mSimpleCursorAFg)
					.commit();
			break;
		}
	}
}
