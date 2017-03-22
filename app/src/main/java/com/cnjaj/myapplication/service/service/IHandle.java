package com.cnjaj.myapplication.service.service;

import android.os.Messenger;

/**
 * Created by Administrator on 2017/3/21.
 */
public interface IHandle {
    void onSessionCreate(Messenger messenger);

    void onSend(String obj);

    void onReceive(String obj, Messenger replyTo);

    void onSessionClosed();
}
