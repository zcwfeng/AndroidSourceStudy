package com.zcwfeng.sourcestudy.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.util.Log;

import com.zcwfeng.sourcestudy.MyApplication;

/**
 * http 的相关操作
 * 
 * @author zhangchuanwei
 * 
 *         2013-12-24
 * 
 */
public class HttpUtil
{

	private static final int CONNECTION_TIMEOUT = 20000;
	private static final int SO_TIMEOUT = 30000;
	public static String PARSER_UNICODE = "UTF-8";
	public static final String TIME_OUT_ERROR = "408";
	public static final String NET_NOTCONNECT = "-1";

	public static final String GBK = "GBK";
	public static final String UTF_8 = "utf-8";

	public static final int DO_POST = 0;
	public static final int DO_GET = 1;

	/**
	 * post
	 * 
	 * @param url
	 * @param nameValuePairs
	 * @throws Exception
	 */
	private static HttpResponse doPost(String url, List<NameValuePair> nameValuePairs) throws Exception
	{
		HttpParams httpParams = new BasicHttpParams();
		setHttpParams(httpParams);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, PARSER_UNICODE));
		HttpResponse httpResponse = httpClient.execute(httpPost);
		int res = httpResponse.getStatusLine().getStatusCode();
		if (200 != res)
			throw new Exception();

		return httpResponse;
	}

	/**
	 * get
	 * 
	 * @param url
	 * @throws Exception
	 */
	private static HttpResponse doGet(String url) throws Exception
	{
		HttpParams httpParams = new BasicHttpParams();
		setHttpParams(httpParams);
		DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		int res = httpResponse.getStatusLine().getStatusCode();
		if (200 != res)
			throw new Exception();

		return httpResponse;
	}

	private static void setHttpParams(HttpParams httpParams)
	{
		HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
	}

	/**
	 * 判断是否有网络连接
	 * 
	 * @param activity
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context)
	{
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					State state = info[i].getState();
					if (state == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否是WIFi网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiNet(Context context)
	{
		ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectMgr.getActiveNetworkInfo();
		if (info.getType() == ConnectivityManager.TYPE_WIFI) {
			Log.d("zcw", "WIFI");
			return true;
		}
		return false;
	}

	/**
	 * 通过post方式获取jsonObject
	 * 
	 * @param url
	 * @param nameValuePairs
	 * @param isGzip
	 * @param stackKey
	 *            跟踪日志的nameKey， 为null时 不进行跟踪
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getJSONObjFromUrlByPost(String url, List<NameValuePair> nameValuePairs, boolean isGzip, String stackKey) throws Exception
	{
		return getJSONObjFromUrl(DO_POST, url, nameValuePairs, isGzip, stackKey);
	}

	/**
	 * 通过get方式从url获取jsonObject
	 * 
	 * @param url
	 * @param isGzip
	 * @param stackKey
	 *            跟踪日志的nameKey， 为null时 不进行跟踪
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getJSONObjFromUrlByGet(String url, boolean isGzip, String stackKey) throws Exception
	{
		return getJSONObjFromUrl(DO_GET, url, null, isGzip, stackKey);
	}

	/**
	 * 通过get方式获取JSONArray
	 * 
	 * @param url
	 * @param isGzip
	 * @param stackKey
	 *            跟踪日志的nameKey， 为null时 不进行跟踪
	 * @return
	 * @throws Exception
	 */
	public static JSONArray getJSONArrayFromUrlByGet(String url, boolean isGzip, String stackKey) throws Exception
	{
		return getJSONArrayFromUrl(DO_GET, url, null, isGzip, stackKey);
	}

	/**
	 * 通过post方式获取JSONArray
	 * 
	 * @param url
	 * @param nameValuePairs
	 * @param isGzip
	 * @param stackKey
	 *            跟踪日志的nameKey， 为null时 不进行跟踪
	 * @return
	 * @throws Exception
	 */
	public static JSONArray getJSONArrayFromUrlByPost(String url, List<NameValuePair> nameValuePairs, boolean isGzip, String stackKey) throws Exception
	{
		return getJSONArrayFromUrl(DO_POST, url, nameValuePairs, isGzip, stackKey);
	}

	/**
	 * 从url获取JSONObject
	 * 
	 * @param type
	 * @param url
	 * @param nameValuePairs
	 * @param isGzip
	 * @param stackKey
	 *            跟踪日志的nameKey， 为null时 不进行跟踪
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getJSONObjFromUrl(int type, String url, List<NameValuePair> nameValuePairs, boolean isGzip, String stackKey) throws Exception
	{
		if (!HttpUtil.isNetworkAvailable(MyApplication.getApplication())) {// 网络无连接处理
			return new JSONObject("{time-out:\"-1\"}");
		}
		String jsonStr = getReturnStrFromUrl(type, url, nameValuePairs, isGzip, stackKey);
		if ("302".equals(jsonStr.trim())) {
			// 超时代码处理
			jsonStr = "{time-out:\"302\"}";
		}
		return new JSONObject(jsonStr);
	}

	/**
	 * 获取JSONArray
	 * 
	 * @param type
	 * @param url
	 * @param nameValuePairs
	 * @param isGzip
	 * @param stackKey
	 *            跟踪日志的nameKey， 为null时 不进行跟踪
	 * @return
	 * @throws Exception
	 */
	private static JSONArray getJSONArrayFromUrl(int type, String url, List<NameValuePair> nameValuePairs, boolean isGzip, String stackKey) throws Exception
	{
		if (!HttpUtil.isNetworkAvailable(MyApplication.getApplication())) {// 网络无连接处理
			return new JSONArray("{time-out:\"-1\"}");
		}
		String jsonStr = getReturnStrFromUrl(type, url, nameValuePairs, isGzip, stackKey);
		if ("302".equals(jsonStr.trim())) {
			// 超时代码处理
			jsonStr = "[{time-out:\"302\"}]";
		}
		return new JSONArray(jsonStr);
	}

	/**
	 * 
	 * 方法说明：
	 * 
	 * @param type
	 *            DO_POST 或者 DO_GET
	 * @param url
	 * @param nameValuePairs
	 * @param isGzip
	 * @param stackKey
	 *            跟踪日志的nameKey， 为null时 不进行跟踪
	 * @return
	 * @throws Exception
	 */
	public static String getReturnStrFromUrl(int type, String url, List<NameValuePair> nameValuePairs, boolean isGzip, String stackKey) throws Exception
	{
		HttpResponse httpResponse = null;
		if (type == DO_POST) {
			httpResponse = HttpUtil.doPost(url, nameValuePairs);
		} else {
			httpResponse = HttpUtil.doGet(url);
		}

		StringBuilder sb = new StringBuilder();
		HttpEntity httpEntity = null;
		InputStream is = null;
		GZIPInputStream gzipIs = null;

		try {
			httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
			if (isGzip) {
				gzipIs = new GZIPInputStream(is);
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, PARSER_UNICODE), 8);
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			if (gzipIs != null) {
				gzipIs.close();
			}
			is.close();
		}
		return sb.toString();
	}

}
