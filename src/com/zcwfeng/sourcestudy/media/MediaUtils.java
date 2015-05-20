package com.zcwfeng.sourcestudy.media;

import android.media.AudioManager;
import android.media.SoundPool;

import com.zcwfeng.sourcestudy.MyApplication;
import com.zcwfeng.sourcestudy.R;

/**
 * Description:<br/>
 * Copyright (C), 2005-2015,David<br/>
 * Thies program is protected by copyright<br/>
 * David. Program name<br/>
 * Date:2015-5-15<br/>
 * 
 * @author David zcwfeng@126.com<br/>
 * @version 1.0
 * 
 */
public class MediaUtils {
	/**
	 * 声明一个SoundPool
	 */
	public static SoundPool sp;
	/**
	 * 键盘音效id
	 */
	public static int music_keyboard;

	public SoundPool getInstanceSoundPool() {
		if (sp == null) {
			try {
				sp = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sp;
	}

	public static void initMusics() {
		try {
			music_keyboard = sp.load(MyApplication.getApplication(),
					R.raw.coin, 1); // 把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void play() {
		try {
			// 左声道，右声道，优先级，是否循环，速率
			sp.play(music_keyboard, 1, 1, 0, 0, 2.0f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
