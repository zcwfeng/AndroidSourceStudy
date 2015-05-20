package com.zcwfeng.sourcestudy.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;

import com.zcwfeng.sourcestudy.R;

public class TestIntentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_intent);
		// intent参数，ComponentName,flags,action,data,category,extras--->Bundle
		// 1.最普通的intent

		Intent mIntent = new Intent();
		mIntent.setClass(TestIntentActivity.this,
				com.zcwfeng.sourcestudy.activity.TestIntentShowActivity.class);

		// Intent mIntent = new Intent(TestIntentActivity.this,
		// com.zcwfeng.sourcestudy.activity.TestIntentShowActivity.class);
		// mIntent.putExtra("data", "最普通的用法");

		// 2 .短信 电话，网页

		// Uri mUri = Uri.parse("tel:13439000000");
		// Intent mIntent = new Intent(Intent.ACTION_DIAL, mUri);
		// Intent mIntent = new Intent(Intent.ACTION_CALL, mUri);

		// Uri mUri = Uri.parse("https://www.baidu.com");
		// Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
		// mIntent.putExtra("sms", "sms_data");

		// Uri mUri = Uri.parse("tel:13439000000");

		// 调用短信程序
		// Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
		// mIntent.putExtra("sms_body", "The SMS text");
		// mIntent.setType("vnd.android-dir/mms-sms");

		// 传送消息
		// Intent mIntent = new Intent(Intent.ACTION_SENDTO, mUri);
		// mIntent.putExtra("sms_body", "The SMS text");
		// startActivity(mIntent);

		// 传送 MMS
		// Uri mUri = Uri.parse("content://media/external/images/media/23");
		// Intent mIntent = new Intent(Intent.ACTION_SEND);
		// mIntent.putExtra("sms_body", "some text");
		// mIntent.putExtra(Intent.EXTRA_STREAM, mUri);
		// mIntent.setType("image/png");

		// 3. 卸载程序
		// Uri uri = Uri.fromParts("package", strPackageName, null);
		// Intent intent = new Intent(Intent.ACTION_DELETE, uri);
		// startActivity(intent);

		// 4. Market 相关
		// 寻找某个应用
		// Uri uri = Uri.parse("market://search?q=pname:pkg_name");
		// Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		// startActivity(intent);
		// where pkg_name is the full package path for an application
		// 显示某个应用的相关信息
		// Uri uri = Uri.parse("market://details?id=app_id");
		// Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		// startActivity(intent);
		// where app_id is the application ID, find the ID
		// by clicking on your application on Market home
		// page, and notice the ID from the address bar

		// 5. 播放多媒体
		// Uri uri = Uri.parse("file:///sdcard/song.mp3");
		// Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		// intent.setType("audio/mp3");
		// startActivity(intent);
		// Uri uri =
		// Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
		// "1");
		// Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		// startActivity(intent);

		// 6.传送Email
		// Uri uri = Uri.parse("mailto:xxx@abc.com");
		// Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
		// startActivity(intent);
		// Intent intent = new Intent(Intent.ACTION_SEND);
		// intent.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
		// intent.putExtra(Intent.EXTRA_TEXT, "The email body text");
		// intent.setType("text/plain");
		// startActivity(Intent.createChooser(intent, "Choose Email Client"));

		// Intent intent=new Intent(Intent.ACTION_SEND);
		// String[] tos={"me@abc.com"};
		// String[] ccs={"you@abc.com"};
		// intent.putExtra(Intent.EXTRA_EMAIL, tos);
		// intent.putExtra(Intent.EXTRA_CC, ccs);
		// intent.putExtra(Intent.EXTRA_TEXT, "The email body text");
		// intent.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
		// intent.setType("message/rfc822");
		// startActivity(Intent.createChooser(intent, "Choose Email Client"));
		// 传送附件
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
		intent.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/mysong.mp3");
		intent.setType("audio/mp3");
		startActivity(Intent.createChooser(intent, "Choose Email Client"));

		// 7. 联系人列表
		// Intent i = new Intent();
		// i.setAction(Intent.ACTION_GET_CONTENT);
		// i.setType("vnd.android.cursor.item/phone");
		// startActivityForResult(i, REQUEST_TEXT);
		//
		// Uri uri = Uri.parse("content://contacts/people");
		// Intent it = new Intent(Intent.ACTION_PICK, uri);
		// startActivityForResult(it, REQUEST_TEXT);

		// 8 打开另一程序
		// Intent i = new Intent();
		// ComponentName cn = new ComponentName("com.yellowbook.android2",
		// "com.yellowbook.android2.AndroidSearch");
		// i.setComponent(cn);
		// i.setAction("android.intent.action.MAIN");
		// startActivityForResult(i, RESULT_OK);

		// 9.打开录音机
		// Intent mi = new Intent(Media.RECORD_SOUND_ACTION);
		// startActivity(mi);

		// 10.从gallery选取图片
		// Intent i = new Intent();
		// i.setType("image/*");
		// i.setAction(Intent.ACTION_GET_CONTENT);
		// startActivityForResult(i, 11);

		// 11.打开照相机
		// Intent i = new Intent(Intent.ACTION_CAMERA_BUTTON, null);
		// this.sendBroadcast(i);

		// long dateTaken = System.currentTimeMillis();
		// String name = createName(dateTaken) + ".jpg";
		// String fileName = folder + name;
		// ContentValues values = new ContentValues();
		// values.put(Images.Media.TITLE, fileName);
		// values.put("_data", fileName);
		// values.put(Images.Media.PICASA_ID, fileName);
		// values.put(Images.Media.DISPLAY_NAME, fileName);
		// values.put(Images.Media.DESCRIPTION, fileName);
		// values.put(Images.ImageColumns.BUCKET_DISPLAY_NAME, fileName);
		// Uri photoUri = getContentResolver().insert(
		// MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
		// Intent inttPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// inttPhoto.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
		// startActivityForResult(inttPhoto, 10);

		// 12.install apk
		// Uri installUri = Uri.fromParts("package", "xxx", null);
		// mIntent = new Intent(Intent.ACTION_PACKAGE_ADDED, installUri);

		// mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// startActivity(mIntent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

	}
}
