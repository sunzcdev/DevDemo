package com.cnjaj.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/5/22.
 */
public class PreferencesBean {
    private static final String CAR_LICENCE = "car_licence";

    private static SharedPreferences getSp(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getSp(context).edit();
    }

    public static void setCarLicence(Context context, String carLicence) {
        SharedPreferences.Editor sp = getEditor(context);
        sp.putString(CAR_LICENCE, carLicence).apply();
    }

    public static String getCarLicence(Context context) {
        return getSp(context).getString(CAR_LICENCE, "");
    }
}
