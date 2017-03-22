package com.cnjaj.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.cnjaj.myapplication.service.service.ServiceBridge;

/**
 * 服务本身就像是个发动机。启动需要外部调用者启动，它提供动力输出。而动力输出的用法服务则不关心。
 * 因此，服务的优势在于它可以提供持续不断的信息输出。
 * 因此凡服务都有bind接口，以及输出信息的接口。目前android 中服务的bind接口是bindService，信息输出的接口是Messenger，Binder，AIDL
 */
public class MyService extends Service {
    private ServiceBridge mServiceBridge;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mServiceBridge = ServiceBridge.bindService(this);
        this.mServiceBridge.setHandler(new TestServiceHandle());
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return mServiceBridge.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mServiceBridge.onBind(intent);
    }
}
