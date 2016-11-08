package com.cnjaj.myapplication.pattern.state;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/1.
 */
public class LogoutState implements UserState {
    LogoutState() {
    }

    LogoutState(Context context, String username) {
        //TODO 在这里清除本地保存的用户数据
        Toast.makeText(context, username + "退出登录", Toast.LENGTH_LONG).show();


    }

    @Override
    public User getUser(Context context, String userName) {
        context.startActivity(new Intent(context, LoginActivity.class));
        return new User();
    }

    @Override
    public void comment(Context context, String comment) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    public void lookGood(Context context) {
        Toast.makeText(context, "好多好多商品啊！", Toast.LENGTH_LONG).show();
    }
}
