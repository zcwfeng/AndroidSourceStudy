package com.zcwfeng.sourcestudy.thirdparty.eventbus;

public class CountDownEvent {
    private int max;
    public CountDownEvent(int max)
    {
        this.max=max;
    }
    public int getMax()
    {
        return max;
    }
}
