package com.zcwfeng.sourcestudy.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zcwfeng.sourcestudy.R;
import com.zcwfeng.sourcestudy.download.StreamTool;


public class DownLoadActivity extends Activity implements OnClickListener {
    //定义相关控件
    ProgressBar bar;
    Button bt;
    TextView tv ;
    EditText et_text;
    boolean flag = true;
    boolean stopflag = false;
    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            bar.setProgress(total);
            int max = bar.getMax();
            if (total>=(max-1)) {
                total = max;
                flag = false;
            }
            int result = total*100/max;
            tv.setText("当前进度："+result+"%");
            super.handleMessage(msg);
        }
        
    };
    int total = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_multithread);
        //初始化相关控件
        bar = (ProgressBar) this.findViewById(R.id.bar);
        bt = (Button)this.findViewById(R.id.bt);
        tv = (TextView)this.findViewById(R.id.tv_process);
        et_text=(EditText)this.findViewById(R.id.et_path);
        bt.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.bt:
            if ("开始下载".equals(bt.getText().toString().trim())) {
                bt.setText("暂停");
                stopflag = false;
            }else {
                bt.setText("开始下载");
                stopflag=true;
            }
            new Thread(){

                @Override
                public void run() {
                    while (flag) {
                        try {
                            sleep(1000);
                            Message message = new Message();
                            //通知更新UI
                            handler.sendMessage(message);
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                    }
                    super.run();
                }
                
            }.start();
            //得到下载路径
            String path = et_text.getText().toString().trim();
            if ("".equals(path)) {
                Toast.makeText(this, "路径不能为空", 0).show();
                return;
            }
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //设置请求的相关参数
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setRequestProperty("User-Agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
                int code = conn.getResponseCode();
                if (code ==200) {
                    int length = conn.getContentLength();
                    RandomAccessFile file = new RandomAccessFile("/mnt/sdcard/" + getFileName(path), "rwd");
                 //设置文件长度
                    file.setLength(length);
                    //设置进度条最大值
                 bar.setMax(length);
                 //建立3条线程下载数据
                 int threadNO = 3;
                 //每条线程下载数据的大小
                 int blocksize = length/threadNO;
                 
                 for (int i = 0; i <threadNO; i++) {
                        int startposition = i*blocksize;//每条线程下载的开始位置
                        int endposition = (i+1)*blocksize;//每条线程下载的结束位置
                        if (i==(threadNO-1)) {
                            endposition = length;//如果整个文件的大小不为线程个数的整数倍，则最后一个线程的结束位置即为文件的总长度
                        }
                        DownLoadTask task = new DownLoadTask(i,path,startposition,endposition);
                        task.start();
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                Toast.makeText(this,"下载出现异常",0).show();
            }
            
            break;
        }
    }
    class DownLoadTask extends Thread{
        int threadid;
        String path;
        int startposition;
        int endposition;
        public DownLoadTask(int threadid, String path, int startposition,
                int endposition) {
            this.threadid = threadid;
            this.path = path;
            this.startposition = startposition;
            this.endposition = endposition;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                //为每个线程创建一个文件用于记录暂停下载时当时已下载的数据大小
                File postionfile = new File("/mnt/sdcard/" + threadid + ".txt");
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                System.out.println("线程" + threadid + "正在下载 " + "开始位置 : "
                        + startposition + "结束位置 " + endposition);
                if (postionfile.exists()) {
                    FileInputStream is = new FileInputStream(postionfile);
                    //处理流信息得到以下载的字节数大小
                    byte[] result = StreamTool.getByte(is); 
                    String str = new String(result);
                    if (!"".equals(str)) {
                        //如果postionfile中有记录，则改变开始下载的位置
                        int newstartposition = Integer.parseInt(str);
                        if (newstartposition>startposition) {
                            startposition = newstartposition;
                        }
                    }
                }
                
                conn.setRequestProperty("Range", "bytes=" + startposition + "-"
                        + endposition);
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setRequestProperty("User-Agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
                InputStream is = conn.getInputStream();
                RandomAccessFile file = new RandomAccessFile("/mnt/sdcard/"
                        + getFileName(path), "rwd");
                file.seek(startposition);
                byte[] buffer = new byte[1024];
                int len = 0;
                int currentPosition = startposition;
                while ((len = is.read(buffer))!=-1) {
                    if (stopflag) {
                        return;
                    }
                    file.write(buffer, 0, len);
                    synchronized (DownLoadActivity.this) {
                        total+=len;
                    }
                    //记录当前线程以下载的数据
                    currentPosition+=len;
                    String position = currentPosition+"";
                    FileOutputStream out = new FileOutputStream(postionfile);
                    //把记录写入文件
                    out.write(position.getBytes());
                    out.flush();
                    out.close();
                    
                }
                file.close();
                System.out.println("线程"+threadid+"下载完毕");
                if (postionfile.exists()) {
                    postionfile.delete();
                }
            } catch (Exception e) {
            }
            super.run();
        }
    }
    public String getFileName(String path){
        int start = path.lastIndexOf("/")+1;
        return path.substring(start, path.length());
    }
}

