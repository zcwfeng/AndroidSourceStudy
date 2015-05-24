package com.zcwfeng.sourcestudy.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcwfeng.sourcestudy.R;

public class GridViewHscroll extends Activity {
	private GridView gridView;
	private GridView gridView2;
	private LayoutInflater inflater;
	private List<String> dataList = new ArrayList<String>();
	private List<String> dataList2 = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_view_hscroll);

		testGridViewHscroll1();
		testGridViewHscroll2();
		
	}

	private void testGridViewHscroll2() {
		gridView2 = (GridView) this.findViewById(R.id.gridview2);
		for (int i = 0; i < 10; i++) {
			dataList2.add("测试" + i);
		}
		inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		GridViewAdapter adapter = new GridViewAdapter();
		gridView2.setAdapter(adapter);
		int size = dataList2.size();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		float density = dm.density;
		int allWidth = (int) (110 * size * density);
		int itemWidth = (int) (100 * density);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				allWidth, LinearLayout.LayoutParams.MATCH_PARENT);
		gridView2.setLayoutParams(params);
		gridView2.setColumnWidth(itemWidth);
		gridView2.setHorizontalSpacing(10);
		gridView2.setStretchMode(GridView.NO_STRETCH);
		gridView2.setNumColumns(size);
	}

	private void testGridViewHscroll1() {
		gridView = (GridView) this.findViewById(R.id.gridview);
		for (int i = 0; i < 10; i++) {
			dataList.add("测试" + i);
		}
		inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		GridViewAdapter adapter = new GridViewAdapter();
		gridView.setAdapter(adapter);
		int size = dataList.size();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		float density = dm.density;
		int allWidth = (int) (110 * size * density);
		int itemWidth = (int) (100 * density);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				allWidth, LinearLayout.LayoutParams.MATCH_PARENT);
		gridView.setLayoutParams(params);
		gridView.setColumnWidth(itemWidth);
		gridView.setHorizontalSpacing(10);
		gridView.setStretchMode(GridView.NO_STRETCH);
		gridView.setNumColumns(size);
	}

	final class GridViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return dataList.size();
		}

		@Override
		public Object getItem(int position) {
			return dataList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = inflater.inflate(R.layout.gridview_item, null);
			TextView textView = (TextView) convertView
					.findViewById(R.id.item_textview);
			String str = dataList.get(position);
			textView.setText(str);
			return convertView;
		}

	}
}
