package com.zcwfeng.sourcestudy.thirdparty.volley;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.R.layout;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class TestVolley extends Activity {
	private RequestQueue mRequestQueue;
	private final String TAG = "TestVolley";
	private String url = "http://app.kzhuo.com.cn/app/ad.php?method=payMode&v=1.0&returnType=json&callType=android&uuid=12345678";
	private ImageView mImageViewVolleTest;
	private String urlImg = "http://www.lecake.com/shop/lecake/theme/xth2/images/index/TN_footbanner_07.jpg";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_volley);
		mRequestQueue = Volley.newRequestQueue(this);
		mImageViewVolleTest = (ImageView) findViewById(R.id.test_volley_img);
		testVolleyRequest();
		testImageLoader();
	}

	/**
	 * 测试Volley框架的使用请求
	 * 
	 * Volley支持http的GET、POST、PUT、DELETE等方法，上面给出了GET方法
	 */
	private void testVolleyRequest() {

		JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url,
				null, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.i(TAG, response.toString());
						// parseJSON(response);
						// va.notifyDataSetChanged();
						// pd.dismiss();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.i(TAG, error.getMessage());
					}
				});
		mRequestQueue.add(jr);

	}

	public void testImageLoader() {
		mRequestQueue = Volley.newRequestQueue(this);
		final LruCache<String, Bitmap> mImageCache = new LruCache<String, Bitmap>(
				20);
		ImageCache imageCache = new ImageCache() {
			@Override
			public void putBitmap(String key, Bitmap value) {
				mImageCache.put(key, value);
			}

			@Override
			public Bitmap getBitmap(String key) {
				return mImageCache.get(key);
			}
		};
		ImageLoader mImageLoader = new ImageLoader(mRequestQueue, imageCache);
		// imageView是一个ImageView实例
		// ImageLoader.getImageListener的第二个参数是默认的图片resource id
		// 第三个参数是请求失败时候的资源id，可以指定为0
		ImageListener listener = ImageLoader.getImageListener(
				mImageViewVolleTest, android.R.drawable.ic_menu_rotate,
				android.R.drawable.ic_delete);
		mImageLoader.get(urlImg, listener);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 1）可以针对某些个request做取消操作：
		// for (Request<?> req : mRequestQueue) {
		// req.cancel();
		// }

		// 2）取消这个队列里的所有请求：
		mRequestQueue.cancelAll(this);

		// 3）可以根据RequestFilter或者Tag来终止某些请求
		// mRequestQueue.cancelAll( new RequestFilter() {});
		// or
		// mRequestQueue.cancelAll(new Object());
	}
}
