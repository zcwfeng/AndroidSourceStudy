package com.zcwfeng.sourcestudy.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class AnimationTestAdapter extends BaseArrayAdapter<String> {
	static ViewHolder holder;
	public ArrayList results;

	public ArrayList getResults() {
		return results;
	}

	public void setResults(ArrayList results) {
		this.results = results;
	}

	public AnimationTestAdapter(Context context, ArrayList<String> resultList,
			View parentView) {
		super(context, resultList, parentView);
		setResults(resultList);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_animation_desc, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.animation_category_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(results.get(position).toString());
		return convertView;
	}

	@Override
	public int getViewTypeCount() {
		return super.getViewTypeCount();
	}

	@Override
	public int getItemViewType(int position) {
		return super.getItemViewType(position);
	}

	static class ViewHolder {
		public TextView name;
	}

}
