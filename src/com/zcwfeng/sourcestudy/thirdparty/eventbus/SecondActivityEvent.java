package com.zcwfeng.sourcestudy.thirdparty.eventbus;

public class SecondActivityEvent {
    private String text;
    public SecondActivityEvent(String text)
    {
        this.text=text;
    }
    public String getText(){
        return text;
    }
}
