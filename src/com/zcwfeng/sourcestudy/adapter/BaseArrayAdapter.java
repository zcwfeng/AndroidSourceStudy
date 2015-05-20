package com.zcwfeng.sourcestudy.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

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
public class BaseArrayAdapter<T> extends ArrayAdapter<T> {
	final LayoutInflater mInflater;
	View parentView;
	Context context;
	ArrayList<T> dataList;

	public BaseArrayAdapter(Context context, ArrayList<T> resultList,
			View parentView) {
		super(context, android.R.layout.simple_list_item_1, resultList);
		this.dataList = resultList;
		this.context = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.parentView = parentView;
	}

	/**
	 * 添加旧数据
	 * 
	 * @param resultList
	 */
	public void appendOlder(ArrayList<T> resultList) {
		setNotifyOnChange(false);
		for (T result : resultList) {
			add(result);
		}
		notifyDataSetChanged();
	}

	/**
	 * 刷新数据
	 * 
	 * @param resultList
	 */
	public void refreshData(ArrayList<T> resultList) {
		clear();
		dataList = resultList;
		setNotifyOnChange(false);
		addAll(resultList);
		notifyDataSetChanged();
	}

	public ArrayList<T> getAllItems() {
		return dataList;
	}

}
