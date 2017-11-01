package com.cnjaj.myapplication.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class DemoService extends Service {
    private static final String TAG = "DemoService";
    private static Conn conn;
    private LocalBinder binder = new LocalBinder();

    public static void bind(Context context) {
        conn = new Conn();
        context.bindService(new Intent(context, DemoService.class), conn, Context.BIND_AUTO_CREATE);
    }

    public static void unBind(Context context) {
        context.unbindService(conn);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate---");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy---");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind------");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind------");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG, "onRebind------");
    }

    private static class Conn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private class LocalBinder extends Binder {
        public DemoService getService() {
            return DemoService.this;
        }
    }
}
