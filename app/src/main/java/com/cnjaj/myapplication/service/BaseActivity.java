package com.cnjaj.myapplication.service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.cnjaj.myapplication.service.service.ClientBridge;
import com.cnjaj.myapplication.service.service.IHandle;

/**
 * Created by Administrator on 2017/3/21.
 */
public abstract class BaseActivity extends AppCompatActivity implements IHandle {
    ClientBridge mClientBridge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClientBridge = ClientBridge.createClient(this);
        mClientBridge.setHandler(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mClientBridge.connectService(MyService.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mClientBridge.close();
    }

}
