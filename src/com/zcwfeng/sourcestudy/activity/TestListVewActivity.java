package com.zcwfeng.sourcestudy.activity;

import java.util.ArrayList;
import java.util.TreeSet;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.R.layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TestListVewActivity extends Activity {

	private ListView mListView;
	private MyCustomAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_list_vew);
		String tag = "%s%s,%d";
		String out = String.format(tag, "张三", "李四", 11);
		Log.e("zcw", "StringFormat:" + out);
		mListView = (ListView) findViewById(R.id.testlistview);

		mAdapter = new MyCustomAdapter();
		for (int i = 1; i < 50; i++) {
			mAdapter.addItem("item " + i);
			if (i % 4 == 0) {
				mAdapter.addSeparatorItem("separator " + i);
			}
		}
		mListView.setAdapter(mAdapter);
	}

	private class MyCustomAdapter extends BaseAdapter {

		private static final int TYPE_ITEM = 0;
		private static final int TYPE_SEPARATOR = 1;
		private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

		private ArrayList<String> mData = new ArrayList<String>();
		private LayoutInflater mInflater;

		private TreeSet mSeparatorsSet = new TreeSet();

		public MyCustomAdapter() {
			mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public void addItem(final String item) {
			mData.add(item);
			notifyDataSetChanged();
		}

		public void addSeparatorItem(final String item) {
			mData.add(item);
			// save separator position
			mSeparatorsSet.add(mData.size() - 1);
			notifyDataSetChanged();
		}

		@Override
		public int getItemViewType(int position) {
			return mSeparatorsSet.contains(position) ? TYPE_SEPARATOR
					: TYPE_ITEM;
		}

		@Override
		public int getViewTypeCount() {
			return TYPE_MAX_COUNT;
		}

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public String getItem(int position) {
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			int type = getItemViewType(position);
			Log.e("zcw", "getView " + position + " " + convertView + " type = "
					+ type);
			if (convertView == null) {
				holder = new ViewHolder();
				switch (type) {
				case TYPE_ITEM:
					convertView = mInflater.inflate(R.layout.item1, null);
					holder.textView = (TextView) convertView
							.findViewById(R.id.text);
					break;
				case TYPE_SEPARATOR:
					convertView = mInflater.inflate(R.layout.item2, null);
					holder.textView = (TextView) convertView
							.findViewById(R.id.textseparator);
					break;
				}
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.textView.setText(mData.get(position));
			return convertView;
		}

	}

	public static class ViewHolder {
		public TextView textView;
	}
}
