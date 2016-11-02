package com.cnjaj.myapplication;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/1.
 */
public class LoginState implements UserState {

    private User user;

    public LoginState(Context context, User user) {
        //TODO 在这里将登录后信息，比如token，保存到本地中，或者设置到cookie中
        this.user = user;
        ViewUtils.saveUser(context, user);
        Toast.makeText(context, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public User getUser(Context context, String userName) {
        //TODO 在这里从本地取出用户信息或者token
        if (user == null) {
            user = ViewUtils.getUser(context, userName);
        }
        return user;
    }

    @Override
    public void comment(Context context, String comment) {
        Toast.makeText(context, comment, Toast.LENGTH_LONG).show();
    }

    @Override
    public void lookGood(Context context) {
        Toast.makeText(context, "好多好多商品啊！", Toast.LENGTH_LONG).show();
    }
}
