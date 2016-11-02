package com.cnjaj.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class ViewUtils {
    private static final String USER_LIST = "userList";
    private static Toast mToast;
    private static List<String> mUsers = new ArrayList<>();

    public static void toast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else
            mToast.setText(msg);
        mToast.show();
    }

    public static void saveUser(Context context, User user) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(user.getName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("age", user.getAge());
        editor.putString("token", user.getToken());
        editor.putString("name", user.getName());
        editor.apply();
    }

    public static User getUser(Context context, String userName) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(userName, Context.MODE_PRIVATE);
        return new User(sp.getString("name", ""), sp.getInt("age", 0), sp.getString("token", ""));
    }

    public static void clearUser(Context context, String userName) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(userName, Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }
}
