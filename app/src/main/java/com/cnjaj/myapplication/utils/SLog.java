package com.cnjaj.myapplication.utils;

import android.support.v4.BuildConfig;

/**
 * 自定义Log类
 * 可以添加一个写入本地，以及写入网络的功能
 * 添加一个解析json，xml的功能
 * 添加一个查询方法执行时间的功能，也就是定点报时的功能。
 * 添加一个自动添加tag的功能，tag可以设置为当前执行的类名
 * Created by Administrator on 2016/11/8.
 */
public class SLog {
    public static boolean DEBUG = !BuildConfig.BUILD_TYPE.equals("release");

    public static void v(String tag, String message) {
        if (DEBUG) {
            android.util.Log.v(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if (DEBUG) {
            android.util.Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (DEBUG) {
            android.util.Log.i(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (DEBUG) {
            android.util.Log.w(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (DEBUG) {
            android.util.Log.e(tag, message);
        }
    }

    public static void e(String tag, String message, Exception e) {
        if (DEBUG) {
            android.util.Log.e(tag, message, e);
        }
    }
}
