package com.cnjaj.myapplication.service.service;

import android.os.Handler;
import android.os.Message;
import android.os.Messenger;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/3/21.
 */
public class StaticHandler extends Handler {
    public static final int SESSION_CREATE = 0;
    public static final int SEND = 1;
    public static final int RECEIVE = 2;
    public static final int SESSION_CLOSED = 3;
    private final WeakReference<IHandle> mContext;

    public StaticHandler(IHandle handle) {
        mContext = new WeakReference<>(handle);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        IHandle handle = mContext.get();
        if (handle == null) return;
        switch (msg.what) {
            case SESSION_CREATE:
                handle.onSessionCreate((Messenger) msg.obj);
                break;
            case SEND:
                handle.onSend((String) msg.obj);
                break;
            case RECEIVE:
                handle.onReceive((String) msg.obj, msg.replyTo);
                break;
            case SESSION_CLOSED:
                handle.onSessionClosed();
                break;
            default:
                break;
        }
    }
}
