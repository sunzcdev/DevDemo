package com.cnjaj.myapplication.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

/**
 * Created by Administrator on 2017/3/21.
 */
public class ServiceBridge {
    private final Service mService;
    private StaticHandler mHandler;
    private Messenger mMessenger;

    public static ServiceBridge bindService(Service service) {
        return new ServiceBridge(service);
    }

    private ServiceBridge(Service context) {
        this.mService = context;
    }

    public void setHandler(IHandle handler) {
        this.mHandler = new StaticHandler(handler);
        this.mMessenger = new Messenger(mHandler);
    }

    public static final String CLIENT = "client";

    private void listenClient(Intent intent) {
        if (intent != null) {
            Messenger clientMessenger = intent.getParcelableExtra(CLIENT);
            Message message = Message.obtain();
            message.what = StaticHandler.SESSION_CREATE;
            message.obj = clientMessenger;
            sendSelfMessage(message);
        }
    }

    private void sendSelfMessage(Message message) {
        this.mHandler.sendMessage(message);
    }

    public IBinder onBind(Intent intent) {
        listenClient(intent);
        return mMessenger.getBinder();
    }

    public boolean onUnbind(Intent intent) {
        return false;
    }
}
