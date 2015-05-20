package com.zcwfeng.sourcestudy.thirdparty.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zcwfeng.sourcestudy.R;

import de.greenrobot.event.EventBus;

public class SecondActivity extends Activity implements View.OnClickListener {
    Button button1,button2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbus_activity_second);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }
 
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.button1:
                finish();
                break;
            case R.id.button2:
                //发送事件，因为MainActivity已经注册过SecondActivityEvent,且没有关闭，所以会接收到
                EventBus.getDefault().post(new SecondActivityEvent("Message From SecondActivity"));
                break;
        }
    }
}