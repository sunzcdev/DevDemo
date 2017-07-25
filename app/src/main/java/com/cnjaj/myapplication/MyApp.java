package com.cnjaj.myapplication;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.jayfeng.lesscode.core.$;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MyApp extends Application {
    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        $.getInstance()
                .context(getApplicationContext())
                .build();
        app = this;
    }

    public static MyApp getApp() {
        return app;
    }
}

