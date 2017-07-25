package com.cnjaj.myapplication.utils;

import android.util.Log;
import com.cnjaj.myapplication.BuildConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义日志输出类
 *
 * @author 李延超 创建日间：2015.06.24
 */
public class MyLog {
    private static String insertRow(String msg) {
        StringBuilder sb = new StringBuilder(msg);
        int position = 100;
        while (position < sb.length()) {
            sb.insert(position, "\n");
            position += 100;
        }
        return sb.toString();
    }

    private static List<String> splitMsg(String msg) {
        msg = insertRow(msg);
        ArrayList<String> msgs = new ArrayList<>();
        if (msg.length() > 4000) {
            for (int i = 0; i < msg.length(); i += 4000) {
                String str;
                if (i + 4000 < msg.length())
                    str = msg.substring(i, i + 4000);
                else
                    str = msg.substring(i, msg.length());
                msgs.add(str);
            }
        } else
            msgs.add(msg);
        return msgs;
    }

    public static void v(Object c, String msg) {
        if (BuildConfig.DEBUG) {
            List<String> msgs = splitMsg(msg);
            for (String msg1 : msgs) {
                Log.v(c.getClass().getSimpleName(), msg1);
            }
        }
    }

    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            List<String> msgs = splitMsg(msg);
            for (String msg1 : msgs) {
                Log.v(tag, msg1);
            }
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            List<String> msgs = splitMsg(msg);
            for (String msg1 : msgs) {
                Log.d(tag, msg1);
            }
        }
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            List<String> msgs = splitMsg(msg);
            for (String msg1 : msgs) {
                Log.i(tag, msg1);
            }
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            List<String> msgs = splitMsg(msg);
            for (String msg1 : msgs) {
                Log.e(tag, msg1);
            }
        }
    }
}
