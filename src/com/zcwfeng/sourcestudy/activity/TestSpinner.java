package com.zcwfeng.sourcestudy.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.adapter.MyAdapter;
import com.zcwfeng.sourcestudy.dataset.Person;

public class TestSpinner extends Activity {
	Spinner mSpinner1;
	Spinner mSpinner2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_spinner);
		mSpinner1 = (Spinner) findViewById(R.id.spinner1);
		mSpinner2 = (Spinner) findViewById(R.id.spinner2);

		// 建立数据源
		String[] mItems = getResources().getStringArray(R.array.spinnernames);
		// 建立Adapter并且绑定数据源
		ArrayAdapter<String> mAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, mItems);
		// 绑定 Adapter到控件
		mSpinner1.setAdapter(mAdapter1);

		mSpinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String str = parent.getItemAtPosition(position).toString();
				Toast.makeText(TestSpinner.this, "你点击的是:" + str, Toast.LENGTH_SHORT)
						.show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		mAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		
		// 建立数据源
		List<Person>  persons=new ArrayList<Person>();
		persons.add(new Person("张三", "上海 "));
		persons.add(new Person("李四", "上海 "));
		persons.add(new Person("王五", "北京" ));
		persons.add(new Person("赵六", "广州 "));
		//  建立Adapter绑定数据源
		MyAdapter mAdapter=new MyAdapter(this, persons);
		//绑定Adapter
		mSpinner2.setAdapter(mAdapter);
	}
}
