package com.zcwfeng.sourcestudy.service;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    static final String TAG="MyService";
    
    //定义内部类MyServiceImpl继承我们的AIDL文件自动生成的内部类，
    //并且实现我们AIDL文件定义的接口方法
    private class MyServiceImpl extends IStockQuoteService.Stub{

        @Override
        public double getPrice(String ticker) throws RemoteException {
            Log.e(TAG, "getPrice");
            return 10.5;
        }
        
    }
    
    @Override
    public IBinder onBind(Intent arg0) {
        //返回AIDL实现
        return new MyServiceImpl();
    }
      
    
    @Override
    public void onDestroy(){
        Log.e(TAG, "Release MyService");
        super.onDestroy();
    }
}