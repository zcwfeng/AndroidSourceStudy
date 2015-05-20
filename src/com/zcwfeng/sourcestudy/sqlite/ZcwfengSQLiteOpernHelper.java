package com.zcwfeng.sourcestudy.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

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
public class ZcwfengSQLiteOpernHelper extends SQLiteOpenHelper {
	public static final int VERSION = 1;
	public static final String DB_NAME = "zcwfeng.db";
	public static final String TABLE_NAME = "MUSIC_USER";

	public ZcwfengSQLiteOpernHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public ZcwfengSQLiteOpernHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table if not exists "
				+ TABLE_NAME
				+ " (id integer primary key autoincrement,name varchar(20),content text,time date )";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < newVersion) {

		} else {
			;
		}
	}

}
