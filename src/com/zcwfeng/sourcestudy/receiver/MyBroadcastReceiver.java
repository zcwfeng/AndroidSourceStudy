package com.zcwfeng.sourcestudy.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Description：<b>短信</b><br/>
 * 
 * @author david
 * 
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

	// action 名称
	String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(SMS_RECEIVED)) {
			// 相关处理 : 地域变换、电量不足、来电来信；
		}
	}

}