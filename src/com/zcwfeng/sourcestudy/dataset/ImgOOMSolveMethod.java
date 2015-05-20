package com.zcwfeng.sourcestudy.dataset;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zcwfeng.sourcestudy.MyApplication;
import com.zcwfeng.sourcestudy.R;

/**
 * Description:<br/>
 * Copyright (C), 2005-2015,David<br/>
 * Thies program is protected by copyright<br/>
 * David. Program name<br/>
 * Date:2015-5-11<br/>
 * 
 * @author David zcwfeng@12com<br/>
 * @version 0
 * 
 */
public class ImgOOMSolveMethod {

	/**
	 * 解决大图片问题
	 */
	public void ioMethodResolve() {
		// 流的方式
		InputStream is = MyApplication.getApplication().getResources()
				.openRawResource(R.drawable.ic_launcher);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = false;
		options.inSampleSize = 10; // width，hight设为原来的十分一
		Bitmap btp = BitmapFactory.decodeStream(is, null, options);
		// 及时的回收
		// if(!bmp.isRecycle() ){
		// bmp.recycle() //回收图片所占的内存
		// system.gc() //提醒系统及时回收
		
		
	}
	
	// private final static int CWJ_HEAP_SIZE = 6* 1024* 1024 ;
	// public static void testVm() {
	// VMRuntime.getRuntime().setMinimumHeapSize(CWJ_HEAP_SIZE);
	// //设置最小heap内存为6MB大小。当然对于内存吃紧来说还可以通过手动干涉GC去处理
	// }

	/**
	 * 以最省内存的方式读取本地资源的图片
	 * 
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap readBitMap(Context context, int resId) {
		// inJustDecodeBounds = true
		// 他只会将宽高返回给你，不会返回一个真的bitmap

		// 1
		// BitmapFactory.Options options = new BitmapFactory.Options();
		// options.inJustDecodeBounds = true;
		// Bitmap bmp = BitmapFactory.decodeFile(path, options);
		/* 这里返回的bmp是null */
		// 这段代码之后，options.outWidth 和 options.outHeight就是我们想要的宽和高了

		// 2
		/* 计算得到图片的高度 */

		/* 这里需要主意，如果你需要更高的精度来保证图片不变形的话，需要自己进行一下数学运算 */
		// int height = options.outHeight * 200 / options.outWidth;
		// options.outWidth = 200；
		// options.outHeight = height;

		// 3
		// 设置恰当的inSampleSize是解决该问题的关键之一。BitmapFactory.Options提供了另一个成员inJustDecodeBounds。
		// BitmapFactory.Options opts = new BitmapFactory.Options();
		// opts.inJustDecodeBounds = true;
		// Bitmap bitmap = BitmapFactory.decodeFile(imageFile, opts);
		// 设置inJustDecodeBounds为true后，decodeFile并不分配空间，但可计算出原始图片的长度和宽度，
		// 即opts.width和opts.height。有了这两个参数，再通过一定的算法，即可得到一个恰当的inSampleSize。

		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}
	
	

	public static Bitmap createImageThumbnail(String filePath) {
		Bitmap bitmap = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, opts);

//		opts.inSampleSize = computeSampleSize(opts, -1, 128 * 128);
		opts.inJustDecodeBounds = false;

		try {
			bitmap = BitmapFactory.decodeFile(filePath, opts);
		} catch (Exception e) {
		}
		return bitmap;
	}

	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);
		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}
		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;
		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));
		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}
		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}
}
