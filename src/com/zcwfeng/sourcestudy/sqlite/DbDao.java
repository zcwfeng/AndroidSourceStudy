package com.zcwfeng.sourcestudy.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Description:<br/>
 * Copyright (C), 2005-2015,David<br/>
 * Thies program is protected by copyright<br/>
 * David. Program name<br/>
 * Date:2015-5-14<br/>
 * 
 * @author David zcwfeng@126.com<br/>
 * @version 1.0
 * 
 */
public class DbDao {
	ZcwfengSQLiteOpernHelper sqliteHelper;

	public DbDao(Context context) {
		if (this.sqliteHelper == null)
			sqliteHelper = new ZcwfengSQLiteOpernHelper(context);
	}

	public void execDao(String sql) {
		SQLiteDatabase sqliteDb = sqliteHelper.getWritableDatabase();
		sqliteDb.execSQL(sql);
		sqliteDb.close();
		sqliteHelper.close();
	}

	public String executeQuery(String sql) {
		SQLiteDatabase sqliteDb = sqliteHelper.getReadableDatabase();

		Cursor cursor = sqliteDb.rawQuery(sql, null);
		StringBuffer buffer = new StringBuffer();
		while (cursor.moveToNext()) {
			buffer.append(cursor.getInt(0)).append(" ")
					.append(cursor.getString(1)).append(" ")
					.append(cursor.getString(2)).append(" ")
					.append(cursor.getString(3)).append(" ");
		}
		cursor.close();
		sqliteDb.close();
		sqliteHelper.close();
		return buffer.toString();
	}
}
