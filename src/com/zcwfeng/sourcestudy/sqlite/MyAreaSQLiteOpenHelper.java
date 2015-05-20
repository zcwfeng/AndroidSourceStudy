package com.zcwfeng.sourcestudy.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyAreaSQLiteOpenHelper extends SQLiteOpenHelper {

	public static final int VERSION = 1;
	public static final String DB_NAME = "myarea_db.sqlite";
	public static final String BEIJING_TABLE = "beijing_myarea";
	public static final String WUHAN_TABLE = "wuhan_myarea";
	public static final String SHENZHEN_TABLE = "shenzhen_myarea";
	public static final String SHANGHAI_TABLE = "shanghai_myarea";
	public static final String NEWS_ID = "id";
	public static final String NEWS_NAME = "name";

	public MyAreaSQLiteOpenHelper(Context context) {
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
		db.execSQL("drop table if exists " + BEIJING_TABLE + ";");
		db.execSQL("drop table if exists " + WUHAN_TABLE + ";");
		db.execSQL("drop table if exists " + SHENZHEN_TABLE + ";");
		db.execSQL("drop table if exists " + SHANGHAI_TABLE + ";");
		createTable(db);
	}

	private void createTable(SQLiteDatabase db) {
		db.execSQL("create table " + BEIJING_TABLE + " (" + NEWS_ID
				+ " String primary key not null, " + NEWS_NAME + " text "
				+ " );"

		);
		db.execSQL("create table " + WUHAN_TABLE + " (" + NEWS_ID
				+ " String primary key not null, " + NEWS_NAME + " text "
				+ " );"

		);
		db.execSQL("create table " + SHENZHEN_TABLE + " (" + NEWS_ID
				+ " String primary key not null, " + NEWS_NAME + " text "
				+ " );"

		);
		db.execSQL("create table " + SHANGHAI_TABLE + " (" + NEWS_ID
				+ " String primary key not null, " + NEWS_NAME + " text "
				+ " );"

		);

	}

}