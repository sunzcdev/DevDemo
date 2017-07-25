package com.cnjaj.myapplication.utils;

import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    /**
     * 按秒计算时间间隔，格式: 2012-07-03 10:10:10
     *
     * @param startDate
     * @param endDate
     * @return interval minute
     */
    public static long timeInterval(String startDate, String endDate) {
        long jg = 0;
        if (("").equals(startDate) || ("").equals(endDate)) {
            return jg;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);
        Date start = null, end = null;
        try {
            start = sdf.parse(startDate);
            end = sdf.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        jg = end.getTime() - start.getTime();
        return jg;
    }

    public static double timeIntervalSS(String startDate, String endDate) {
        double jg = 0.0;
        if (("").equals(startDate) || ("").equals(endDate)) {
            return jg;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);
        Date start = null, end = null;
        try {
            start = sdf.parse(startDate);
            end = sdf.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        jg = ((end.getTime() - start.getTime()) / 1000);
        jg = jg / 60;
        return jg;
    }

    // 获取当前时间，以yy-MM-dd HH:mm:ss格式
    public static String getDateTime() {
        return mill2DateString("yyMMddHHmmss");
    }

    public static String getDate() {
        return mill2DateString("yyMMdd");
    }

    // 获取当前时间，以yyyy-MM-dd HH:mm:ss格式
    public static String getCurrentDateTime() {
        return mill2DateString("yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentDate() {
        return mill2DateString("yyyy-MM-dd");
    }

    // 获取当前时间，以yyyy-MM-dd HH:mm:ss格式
    public static String getCurrentDateTimeJ() {
        return mill2DateString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentTime() {
        return mill2DateString("HHmmss");
    }

    /**
     * 毫秒转换成时分秒
     */
    public static String millsToHMS2(long millis) {
        String time = "00.00";
        long h = millis / 3600000L; // 分;
        // 格式化分钟的格式
        String hour;
        if (h < 10) {
            // hour = "0" + h;
            hour = h + "";
        } else {
            hour = String.valueOf(h);
        }

        long m = (millis / 60000) % 60L; // 分;
        // 格式化分钟的格式
        String minute;
        if (m < 10) {
            minute = "0" + m;
        } else {
            minute = String.valueOf(m);
        }
        time = hour + "." + minute;
        return time;
    }

    /**
     * 毫秒转换成时分秒
     */
    public static Spanned millsToHMS(long millis) {
        String time = "00小时00分钟00秒";
        Spanned str;
        long h = millis / 3600000L; // 分;
        // 格式化分钟的格式
        String hour;
        if (h < 10) {
            hour = "0" + h;
            // hour = h+"";
        } else {
            hour = String.valueOf(h);
        }

        long m = (millis / 60000) % 60L; // 分;
        // 格式化分钟的格式
        String minute;
        if (m < 10) {
            minute = "0" + m;
        } else {
            minute = String.valueOf(m);
        }
        // 格式化秒钟的格式
        long ss = (millis / 1000L) % 60L; // 秒
        String second;
        if (ss < 10) {
            second = "0" + ss;
        } else {
            second = String.valueOf(ss);
        }
        time = hour + "小时" + minute + "分钟" + second + "秒";

        str = Html.fromHtml(time.toString());

        ((Spannable) str).setSpan(new AbsoluteSizeSpan(30), 0, 2,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ((Spannable) str).setSpan(new AbsoluteSizeSpan(30), 4, 6,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ((Spannable) str).setSpan(new AbsoluteSizeSpan(30), 8, 10,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return str;
    }

    public static Spanned todayMes(String mile, String speed) {
        String time = "00米 00KM/S";
        // time = mile+"里程"+speed+"KM/H";
        time = speed + "KM/H";
        Spanned str = null;
        try {
            str = Html.fromHtml(time.toString());
            ((Spannable) str).setSpan(new AbsoluteSizeSpan(30), 0,
                    time.indexOf('K'), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // ((Spannable) str).setSpan(new
            // AbsoluteSizeSpan(45),time.indexOf('程')+1,time.indexOf('K'),
            // Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return str;
    }

    public static String getYearMonthDayHourMinuteSecond(long timeMillis) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.setTimeInMillis(timeMillis);
        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH) + 1;
        String mToMonth = null;
        if (String.valueOf(month).length() == 1) {
            mToMonth = "0" + month;
        } else {
            mToMonth = String.valueOf(month);
        }

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dToDay = null;
        if (String.valueOf(day).length() == 1) {
            dToDay = "0" + day;
        } else {
            dToDay = String.valueOf(day);
        }

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String hToHour = null;
        if (String.valueOf(hour).length() == 1) {
            hToHour = "0" + hour;
        } else {
            hToHour = String.valueOf(hour);
        }

        int minute = calendar.get(Calendar.MINUTE);
        String mToMinute = null;
        if (String.valueOf(minute).length() == 1) {
            mToMinute = "0" + minute;
        } else {
            mToMinute = String.valueOf(minute);
        }

        int second = calendar.get(Calendar.SECOND);
        String sToSecond = null;
        if (String.valueOf(second).length() == 1) {
            sToSecond = "0" + second;
        } else {
            sToSecond = String.valueOf(second);
        }
        return year + "年" + mToMonth + "月" + dToDay + "日  " + hToHour + ":"
                + mToMinute + ":" + sToSecond;
    }

    /**
     * 根据日期算出星期
     *
     * @param pTime
     * @return
     */
    public static String getWeek(String pTime) {

        String Week = "";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {

            c.setTime(format.parse(pTime));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "六";
        }

        return "星期" + Week;
    }

    /**
     * yyyyMMddHHmmss转换成yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     * @throws Exception
     */
    public static String changeTime(String time) {
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = oldFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newFormat.format(date);
    }

    public static String mill2DateString(long mill, String template) {
        SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.CHINA);
        Date currentDate;
        if (mill == 0) {
            currentDate = new Date();
        } else
            currentDate = new Date(mill);
        return sdf.format(currentDate);
    }

    public static String mill2DateString(String template) {
        return mill2DateString(0, template);
    }

    /**
     * yyyy-MM-dd HH:mm:ss 转换成yyyyMMddHHmmss
     *
     * @param time
     * @return
     * @throws Exception
     */
    public static String changeTime1(String time) throws Exception {

        SimpleDateFormat newFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = oldFormat.parse(time);
        return newFormat.format(date);
    }

    public static String getTimeStr(long startDate) {
        return mill2DateString(startDate, "HH:mm:ss");
    }
}
