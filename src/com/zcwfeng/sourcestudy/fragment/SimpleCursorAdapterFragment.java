package com.zcwfeng.sourcestudy.fragment;

import com.zcwfeng.sourcestudy.R;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Description:<br/>
 * Copyright (C), 2005-2015,David<br/>
 * Thies program is protected by copyright<br/>
 * David. Program name<br/>
 * Date:2015-5-1<br/>
 * 
 * @author David zcwfeng@126.com<br/>
 * @version 1.0
 * 
 */
public class SimpleCursorAdapterFragment extends AbstractFragment implements
		OnClickListener {

	private View mRootView;
	private ListView mListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_simplecursor_adapter,
				container, false);
		initView();
		return mRootView;
	}

	private void initView() {
		mListView = (ListView) mRootView.findViewById(R.id.simplecursor_listview);
		Cursor myCursor = getActivity().getContentResolver().query(
				People.CONTENT_URI, null, null, null, null);// 是系统里的表
		getActivity().startManagingCursor(myCursor);
		ListAdapter listAdapter = new SimpleCursorAdapter(getActivity(), // 上下文
				android.R.layout.simple_expandable_list_item_1, // 要显示的listview的样式
				myCursor, // 数据源
				new String[] { People.NAME }, // 所对应的字段
				new int[] { android.R.id.text1 } // 上面的字段显示在界面上的那个控件
		);
		mListView.setAdapter(listAdapter);
	}

	@Override
	public void onClick(View v) {
	}

}
