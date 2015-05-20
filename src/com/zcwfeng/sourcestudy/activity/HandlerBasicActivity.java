package com.zcwfeng.sourcestudy.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.R.drawable;
import com.zcwfeng.sourcestudy.R.id;
import com.zcwfeng.sourcestudy.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerBasicActivity extends Activity {
	private Thread mThread = null;
	private Handler mHandler = null;
	private Handler mInnerHandler = null;
	private TextView myText;

	private Handler mainThreadHandler;
	public Handler subthreadHandler;

	private Handler normalHandler;
	private Handler superHandler;
	private ViewStub pic_sub; 
	// runOnUiThread
	// public final void runOnUiThread(Runnable action) {
	// if (Thread.currentThread() != mUiThread) {
	// mHandler.post(action);//将Runnable
	// Post到消息队列，由内部的mHandler来处理，实际上也是Handler的处理方式
	// } else {
	// action.run();//已经在UI线程，直接运行。
	// }
	// } 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler_basic);
		
		
		myText = (TextView) findViewById(R.id.text);
		// testHandler();
		// // 子线程向主线程发送消息，主线程处理ui
		// testHandler2();
		// //主线程箱子线程发送消息，子线程任务处理完毕，向主线程发送更新ui消息
		testHandler3();
		// handler callback 运用
		// testHandler4();
		
		
		initViewStubTest();
	}

	private void initViewStubTest() {
		 final Handler viewStubhandler = new Handler() {  
		        public void handleMessage(android.os.Message msg) {  
		            View pic_view = pic_sub.inflate();// ①  
		            //pic_sub.setVisibility(View.VISIBLE);// ②  
		            ImageView iv_pic = (ImageView) pic_view.findViewById(R.id.iv_pic);  
		            iv_pic.setImageResource(R.drawable.ic_launcher);  
		            View view = findViewById(R.id.pic_stub);//③  
		            view = findViewById(R.id.pic_view_id_after_inflate);//④  
		        };  
		    };  
		pic_sub = (ViewStub) findViewById(R.id.pic_stub);  
        new Timer().schedule(new TimerTask() {  
  
            @Override  
            public void run() {  
            	viewStubhandler.sendEmptyMessage(1);  
            }  
        }, 1000);// 延迟1秒,然后加载 		
	}

	class MyHandlerCallback implements Handler.Callback {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case 222:

				break;

			default:
				break;
			}
			return false;
		}

	}

	private void testHandler4() {

		NormalThread thread = new NormalThread();
		thread.start();

		HandlerThread handlerThread = new HandlerThread("super threa");
		handlerThread.start();

		superHandler = new Handler(handlerThread.getLooper()) {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				int what = msg.what;

				if (what == 2) {
					Log.d("zcw", Thread.currentThread().getName()
							+ " HandlerThread is OK");
				}
			}
		};

	}

	/**
	 * 正常线程
	 * 
	 * @author david
	 * 
	 */
	class NormalThread extends Thread {
		@Override
		public void run() {
			Looper.prepare();
			normalHandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					int what = msg.what;
					if (what == 200) {
						Log.d("zcw", Thread.currentThread().getName()
								+ " Normal is OK");
					}
				}
			};
			Looper.loop();
		}

	}

	/**
	 * 
	 * 向普通线程发送数据
	 * 
	 * @param view
	 */
	public void normalThreadUse(View view) {

		if (normalHandler == null) {
			return;
		}

		normalHandler.sendEmptyMessage(1);
	}

	/**
	 * 
	 * 向HandlerThread发送数据
	 * 
	 * @param view
	 */
	public void handlerThreadUse(View view) {

		if (superHandler == null) {
			return;
		}

		superHandler.sendEmptyMessage(2);
	}

	class SubThread implements Runnable {

		@Override
		public void run() {

			Looper.prepare();
			subthreadHandler = new Handler() {
				public void handleMessage(Message msg) {
					switch (msg.what) {
					case 11:

						Log.i("zcw", "无法刷新界面" + msg.obj.toString());
						// 打印完毕通知界面刷新
						Message msgSub = new Message();
						msgSub.what = 111;
						mainThreadHandler.sendMessage(msgSub);
						break;

					default:
						break;
					}
				};
			};
			Looper.loop();

		}

	}

	private void testHandler3() {

		SubThread mSubThread = new SubThread();
		mainThreadHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case 111:

					Toast.makeText(getApplicationContext(),
							"sub thread looper", Toast.LENGTH_LONG).show();
					myText.setText("子线程接受主线程消息，刷新界面");
					break;

				default:
					break;
				}
			}
		};

		HandlerThread handlerThread = new HandlerThread("主线程回调");
		handlerThread.start();

		new Thread(mSubThread).start();

		myText.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				subthreadHandler.obtainMessage(11, "demo").sendToTarget();
			}
		});

	}

	private void testHandler2() {
		// 子线程向主线程发送消息
		// 主线程接受消息结果处理界面
		// 子线程好事操作完事给主线程发消息刷新界面
		final Handler mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case 10:
					myText.setText("主线程接受子线程消息结果刷新界面》下面主线程向另外子线程发送消息");
					sendEmptyMessage(11);
					break;
				default:
					break;
				}
			}

		};

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					Message msg = new Message();
					msg.what = 10;
					msg.arg1 = 100;

					mHandler.sendMessage(msg);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void testHandler() {
		mThread = new Thread(new Runnable() {

			@Override
			public void run() {
				Looper.prepare();
				mHandler = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						super.handleMessage(msg);
						if (msg.what == 123) {
							Log.i("zcw", "收到消息  msg.arg1=" + msg.arg1);
						}

						new Thread(new Runnable() {

							@Override
							public void run() {
								Log.i("zcw", "我是子线程的内嵌子线程");

							}
						}).start();

					}
				};

				mInnerHandler = new Handler() {
					@Override
					public void handleMessage(Message msg) {
						super.handleMessage(msg);
						if (msg.what == 456) {
							Log.i("zcw", "内嵌handler" + msg.arg1);
						}
					}
				};

				Message message = new Message();
				message.what = 123;
				message.arg1 = 456;
				mHandler.sendMessage(message);

				Message message2 = new Message();
				message2.what = 456;
				message2.arg1 = 456;
				mInnerHandler.sendMessage(message2);
				Looper.loop();

			}
		});
		mThread.start();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
