package com.cnjaj.myapplication;

import android.app.Application;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MyApp extends Application {
    private UserState mUserState = new LogoutState();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void setUserState(UserState userState) {
        mUserState = userState;
    }


    public UserState getCurrentState() {
        return mUserState;
    }

}

