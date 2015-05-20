package com.zcwfeng.sourcestudy.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotifierSQLiteOpenHelper extends SQLiteOpenHelper {
	
	public static final int VERSION = 1;
	public static final String DB_NAME = "notifier_db.sqlite";
	public static final String NEWS_TABLE = "notifier";
	public static final String NEWS_ID = "id";
	public static final String NEWS_TITLE = "title";
	public static final String NEWS_MESSAGE = "message";
	public static final String NEWS_URL = "url";
	public static final String NEWS_DATE = "date";
	public static final String NEWS_ORDERBYDATE = "order_date";
	public static final String NEWS_STATUS = "status";
	
	

	public NotifierSQLiteOpenHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		dropAndCreate(db);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	private void dropAndCreate(SQLiteDatabase db) {
		db.execSQL("drop table if exists " + NEWS_TABLE + ";");
		createTable(db);
	}

	private void createTable(SQLiteDatabase db) {
		db.execSQL("create table " + NEWS_TABLE + " (" + 
				NEWS_ID + " String primary key not null, " +
				NEWS_TITLE + " text, " + 
				NEWS_DATE + " text, " + 
				NEWS_ORDERBYDATE + " text, " + 
				NEWS_MESSAGE + " text, " + 
				NEWS_STATUS + " text, " + 
				NEWS_URL + " text " +" );"
				
				);
				
	}


}
