package com.zcwfeng.sourcestudy.activity;

import com.zcwfeng.sourcestudy.contentprovider.MyUsers;
import com.zcwfeng.sourcestudy.contentprovider.MyUsers.User;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class MyContentDemo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        insertRecord("MyUser");
        displayRecords();
    }
   
    private void insertRecord(String userName) {
        ContentValues values = new ContentValues();
        values.put(MyUsers.User.USER_NAME, userName);
        getContentResolver().insert(MyUsers.User.CONTENT_URI, values);
    }

    private void displayRecords() {
        String columns[] = new String[] { MyUsers.User._ID, MyUsers.User.USER_NAME };
        Uri myUri = MyUsers.User.CONTENT_URI;
        Cursor cur = managedQuery(myUri, columns,null, null, null );
        if (cur.moveToFirst()) {
            String id = null;
            String userName = null;
            do {
                id = cur.getString(cur.getColumnIndex(MyUsers.User._ID));
                userName = cur.getString(cur.getColumnIndex(MyUsers.User.USER_NAME));
                Toast.makeText(this, id + " " + userName, Toast.LENGTH_LONG).show();
           } while (cur.moveToNext());
       }
    }
}