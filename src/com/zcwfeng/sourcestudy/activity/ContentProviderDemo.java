package com.zcwfeng.sourcestudy.activity;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.R.layout;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.Contacts.People;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ContentProviderDemo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content_provider_demo);
		displayRecords();
	}

	private void displayRecords() {
		// 该数组中包含了所有要返回的字段
		String columns[] = new String[] { People.NAME, People.NUMBER };
		Uri mContacts = People.CONTENT_URI;
		Cursor cur = managedQuery(mContacts, columns, // 要返回的数据字段
				null, // WHERE子句
				null, // WHERE 子句的参数
				null // Order-by子句
		);
		if (cur.moveToFirst()) {
			String name = null;
			String phoneNo = null;
			do {
				// 获取字段的值
				name = cur.getString(cur.getColumnIndex(People.NAME));
				phoneNo = cur.getString(cur.getColumnIndex(People.NUMBER));
				Toast.makeText(this, name + "" + phoneNo, Toast.LENGTH_LONG)
						.show();
			} while (cur.moveToNext());
		}
	}

	/**
	 * 修改记录: 我们可以使用ContentResolver.update()方法来修改数据，我们来写一个修改数据的方法:
	 * 
	 * @param recNo
	 * @param name
	 */
	private void updateRecord(int recNo, String name) {
		Uri uri = ContentUris.withAppendedId(People.CONTENT_URI, recNo);
		ContentValues values = new ContentValues();
		values.put(People.NAME, name);
		getContentResolver().update(uri, values, null, null);
	}

	/**
	 * 添加记录: 要增加记录，我们可以调用ContentResolver.insert()方法，该方法接受一个要增加的记录的目标URI，
	 * 以及一个包含了新记录值的Map对象，调用后的返回值是新记录的URI，包含记录号。 上面的例子中我们都是基于联系人信息簿这个标准的Content
	 * Provider，现在我们继续来创建一个insertRecord() 方法以对联系人信息簿中进行数据的添加：
	 * 
	 * @param name
	 * @param phoneNo
	 */
	private void insertRecords(String name, String phoneNo) {
	    ContentValues values = new ContentValues();
	    values.put(People.NAME, name);
	    Uri uri = getContentResolver().insert(People.CONTENT_URI, values);
	    Log.d("ANDROID", uri.toString());
	    Uri numberUri = Uri.withAppendedPath(uri, People.Phones.CONTENT_DIRECTORY);
	    values.clear();
	    values.put(Contacts.Phones.TYPE, People.Phones.TYPE_MOBILE);
	    values.put(People.NUMBER, phoneNo);
	    getContentResolver().insert(numberUri, values);
	}

	/**
	 * 删除记录: Content
	 * Provider中的getContextResolver.delete()方法可以用来删除记录，下面的记录用来删除设备上所有的联系人信息：
	 */
	private void deleteRecords() {
		Uri uri = People.CONTENT_URI;
		getContentResolver().delete(uri, null, null);
	}
}
