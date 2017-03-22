package com.cnjaj.myapplication.service;

import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.cnjaj.myapplication.service.service.IHandle;
import com.cnjaj.myapplication.service.service.StaticHandler;
import com.cnjaj.myapplication.utils.SLog;

public class TestServiceHandle implements IHandle {
    @Override
    public void onSessionCreate(Messenger messenger) {
        SLog.i("当前服务端接收到会话建立");
        Message msg = Message.obtain();
        msg.obj = "你好";
        msg.what = StaticHandler.RECEIVE;
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSend(String obj) {

    }

    @Override
    public void onReceive(String obj, Messenger replyTo) {
        if (replyTo == null) return;
        Message msg = Message.obtain();
        msg.obj = "我收到数据了:" + obj;
        msg.what = StaticHandler.RECEIVE;
        try {
            replyTo.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSessionClosed() {

    }
}
