package com.example.lenovo.learning;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by lenovo on 2017/3/1.
 */

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
