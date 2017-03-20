package com.xznn.hongyang.ui;


import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.xznn.hongyang.base.Cockroach;

/**
 * Created by wuming on 16/10/13.
 */

public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication instance;

//    private static final Thread initSDKThread = new Thread() {
//        @Override
//        public void run() {
//            Log.e(TAG, "====== MyApplication{} ... run() ======");
//        }
//    };

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //-----------------------
        Cockroach.install(new Cockroach.ExceptionHandler() {

            // handlerException内部建议手动try{  你的异常处理逻辑  }catch(Throwable e){ } ，以防handlerException内部再次抛出异常，导致循环调用handlerException

            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.d("Cockroach", thread + "\n" + throwable.toString());
                            throwable.printStackTrace();
                            Toast.makeText(MyApplication.this, "Exception Happend\n" + thread + "\n" + throwable.toString(), Toast.LENGTH_SHORT).show();
//                        throw new RuntimeException("..."+(i++));
                        } catch (Throwable e) {
                            Toast.makeText(MyApplication.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // 卸载代码
//        Cockroach.uninstall();
        //-----------------------

        instance = this;
//        initSDKThread.start();
    }
}
