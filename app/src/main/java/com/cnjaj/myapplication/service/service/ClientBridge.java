package com.cnjaj.myapplication.service.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.cnjaj.myapplication.service.MyService;

import static com.cnjaj.myapplication.service.service.StaticHandler.RECEIVE;
import static com.cnjaj.myapplication.service.service.StaticHandler.SEND;

/**
 * Created by Administrator on 2017/3/22.
 */
public class ClientBridge {
    private final Context mContext;
    private StaticHandler mHandler;
    private Messenger mMessenger;
    private Messenger mServiceMessenger;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Message message = Message.obtain();
            message.what = StaticHandler.SESSION_CREATE;
            mServiceMessenger = new Messenger(service);
            message.obj = mServiceMessenger;
            mHandler.sendMessage(message);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Message message = Message.obtain();
            message.what = StaticHandler.SESSION_CLOSED;
            mHandler.sendMessage(message);
        }
    };

    private ClientBridge(Context context) {
        this.mContext = context;
    }

    public static ClientBridge createClient(Context context) {
        return new ClientBridge(context);
    }

    public void sendSelfMsg(String text) {
        Message selfMsg = Message.obtain();
        selfMsg.obj = text;
        selfMsg.what = SEND;
        mHandler.sendMessage(selfMsg);
    }

    public void setHandler(IHandle handler) {
        this.mHandler = new StaticHandler(handler);
        this.mMessenger = new Messenger(mHandler);
    }

    public void close() {
        mContext.unbindService(mConnection);
    }

    public <T extends MyService> void connectService(Class<T> action) {
        Intent service = new Intent(mContext, action);
        service.putExtra(ServiceBridge.CLIENT, mMessenger);
        mContext.bindService(service, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void send(String text) {
        sendSelfMsg(text);
        Message message = Message.obtain();
        message.what = RECEIVE;
        message.obj = text;
        try {
            mServiceMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendByReply(String text) {
        Message message = Message.obtain();
        message.what = RECEIVE;
        message.replyTo = mMessenger;
        message.obj = text;
        try {
            mServiceMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
