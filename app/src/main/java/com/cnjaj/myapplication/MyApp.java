package com.cnjaj.myapplication;

import android.app.Application;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MyApp extends Application {
    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static MyApp getApp() {
        return app;
    }
}

