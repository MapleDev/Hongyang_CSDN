package com.xznn.hongyang.ui;


import android.app.Application;

/**
 * Created by wuming on 16/10/13.
 */

public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication instance;

    private static final Thread initSDKThread = new Thread() {
        @Override
        public void run() {
        }
    };

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initSDKThread.start();
    }
}
