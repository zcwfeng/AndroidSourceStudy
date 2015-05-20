package com.zcwfeng.sourcestudy.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.adapter.AnimationTestAdapter;

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
public class EnterTestAnimationFragment extends AbstractFragment implements
		OnClickListener {
	private final String TAG = "EnterTestAnimationFragment";
	private View mRootView;
	private ListView mListView;
	private ArrayList mList;
	private AnimationTestAdapter mAdapter;
	private TestViewAnimationFragment mViewAnimationFragment;
	private TestViewAnimationFragmentProperty mViewAnimationFragmentProperty;

	Button testLayoutAnimBtn;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.e(TAG, "onAttach");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.e(TAG, "onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.e(TAG, "onResume");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.e(TAG, "onActivityCreated");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate");

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "onDestroy");

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.e(TAG, "onSaveInstanceState");

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e(TAG, "onDestroyView");

	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.e(TAG, "onDetach");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e(TAG, "onCreateView");

		mRootView = inflater.inflate(R.layout.enter_test_animation_layout,
				container, false);

		mListView = (ListView) mRootView.findViewById(R.id.animation_list);
		if (mList == null || mList.size() < 1)
			fillArray();
		mAdapter = new AnimationTestAdapter(getActivity(), mList, null);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					if (mViewAnimationFragment == null)
						mViewAnimationFragment = new TestViewAnimationFragment();
					switchFragmentbyAdd(R.id.fragment_containe,
							mViewAnimationFragment, "currentfragment");
					break;
				case 2:
					if (mViewAnimationFragmentProperty == null)
						mViewAnimationFragmentProperty = new TestViewAnimationFragmentProperty();
					switchFragmentbyAdd(R.id.fragment_containe,
							mViewAnimationFragmentProperty, "currentfragment");
					break;
				default:
					break;
				}
			}
		});
		testLayoutAnimBtn = (Button) mRootView
				.findViewById(R.id.test_layout_ani);
		testLayoutAnimBtn.setOnClickListener(new LayoutButtonListener());
		return mRootView;
	}

	@Override
	public void onClick(View v) {

	}

	private void fillArray() {
		if (mList == null)
			mList = new ArrayList<>();
		mList.add("View Animation");
		mList.add("Drawable Animation");
		mList.add("Property Animation");
	}

	private class LayoutButtonListener implements OnClickListener {

		public void onClick(View v) {

			Animation animation = (Animation) AnimationUtils.loadAnimation(
					getActivity(), R.anim.list_anim);

			LayoutAnimationController controller = new LayoutAnimationController(
					animation);

			controller.setOrder(LayoutAnimationController.ORDER_NORMAL);

			controller.setDelay(0.5f);

			mListView.setLayoutAnimation(controller);

		}

	}
}
