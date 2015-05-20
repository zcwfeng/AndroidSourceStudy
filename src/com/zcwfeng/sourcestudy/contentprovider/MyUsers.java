package com.zcwfeng.sourcestudy.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class MyUsers {
    public static final String AUTHORITY  = "com.zcwfeng.sourcestudy.contentprovider.MyContentProvider";

    // BaseColumn类中已经包含了 _id字段
   public static final class User implements BaseColumns {
        public static final Uri CONTENT_URI  = Uri.parse("content://com.zcwfeng.sourcestudy.contentprovider.MyContentProvider");
        // 表数据列
        public static final String  USER_NAME  = "USER_NAME";
    }
}