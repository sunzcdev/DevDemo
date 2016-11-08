package com.cnjaj.myapplication.pattern.state;

import android.content.Context;

/**
 * Created by Administrator on 2016/11/1.
 */
public interface UserState {
    User getUser(Context context, String userName);

    void comment(Context context, String comment);

    void lookGood(Context context);
}
