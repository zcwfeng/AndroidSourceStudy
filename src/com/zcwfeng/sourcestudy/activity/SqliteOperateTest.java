package com.zcwfeng.sourcestudy.activity;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.R.layout;
import com.zcwfeng.sourcestudy.sqlite.DbDao;
import com.zcwfeng.sourcestudy.sqlite.ZcwfengSQLiteOpernHelper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class SqliteOperateTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite_operate_test);
		doDb();
	}

	public void doDb() {
		DbDao dbDao = new DbDao(getApplicationContext());
		dbDao.execDao("insert into "
				+ ZcwfengSQLiteOpernHelper.TABLE_NAME
				+ " values(null,'zcwfeng','mayyousucceed','2015-05-14 22:22:22')");
		dbDao.execDao("update " + ZcwfengSQLiteOpernHelper.TABLE_NAME
				+ " set name='zcw' where id=1");

		String result = dbDao.executeQuery("select * from "
				+ ZcwfengSQLiteOpernHelper.TABLE_NAME + " order by id desc");
		Log.e("db", result);
		dbDao.execDao("delete from " + ZcwfengSQLiteOpernHelper.TABLE_NAME
				+ " where id=2");

	}
}
