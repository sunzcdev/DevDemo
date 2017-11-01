package com.cnjaj.myapplication;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

    public static boolean checkPassword(String inputPw) {
        if (TextUtils.isEmpty(inputPw)) return false;
        File propDir = new File("/sdcard/jzjprop/");
        if (!propDir.exists()) {
            return false;
        }
        File file = new File(propDir, "jzj.about");
        if (!file.exists()) return false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String txt = reader.readLine();
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(txt)) {
                sb.append(txt.indexOf('D'));
                sb.append(txt.indexOf('@'));
                sb.append(txt.indexOf('&'));
                sb.append(txt.indexOf('i'));
                sb.append(txt.indexOf('2'));
                sb.append(txt.indexOf('v'));
                if (TextUtils.equals(sb.toString(), inputPw)) return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
