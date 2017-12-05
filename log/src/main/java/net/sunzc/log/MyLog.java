package net.sunzc.log;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 自定义日志输出类
 */
public class MyLog {
    private static ILog LOG, debugLog, releaseLog;

    public static void setDebugLog(ILog debugLog) {
        MyLog.debugLog = debugLog;
    }

    public static void setReleaseLog(ILog releaseLog) {
        MyLog.releaseLog = releaseLog;
    }

    public static void setDebug(boolean isDebug) {
        if (isDebug) {
            if (debugLog == null) {
                debugLog = new AndroidLog();
            }
        } else {
            if (releaseLog == null) {
                releaseLog = new ReleaseLog();
            }
        }
        LOG = isDebug ? debugLog : releaseLog;
    }

    public static void v(Object c, String msg) {
        LOG.v(c, msg);
    }

    public static void v(String tag, String msg) {
        LOG.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        LOG.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        LOG.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        LOG.e(tag, msg);
    }

    public static void e(String tag, String msg, Exception e) {
        LOG.e(tag, msg, e);
    }

    public static void w(String tag, String msg) {
        LOG.w(tag, msg);
    }

    /**
     * 公共Log接口
     */
    public interface ILog {
        void v(String tag, String msg);

        void v(Object tag, String msg);

        void d(String tag, String msg);

        void i(String tag, String msg);

        void e(String tag, String msg);

        void e(String tag, String msg, Exception e);

        void w(String tag, String msg);
    }

    /**
     * 软件发布后的log输出方式
     */
    public static class ReleaseLog extends DefaultLog {
    }

    /**
     * 默认的log输出方式
     */
    public static class DefaultLog implements ILog {

        @Override
        public void v(String tag, String msg) {

        }

        @Override
        public void v(Object tag, String msg) {

        }

        @Override
        public void d(String tag, String msg) {

        }

        @Override
        public void i(String tag, String msg) {

        }

        @Override
        public void e(String tag, String msg) {
        }

        @Override
        public void e(String tag, String msg, Exception e) {
        }

        @Override
        public void w(String tag, String msg) {

        }
    }

    /**
     * 使用android sdk自带的log输出
     */
    private static class AndroidLog extends DefaultLog {

        @Override
        public void v(String tag, String msg) {
            Log.v(tag, msg);
        }

        @Override
        public void v(Object tag, String msg) {
            Log.v(tag.getClass().getSimpleName(), msg);
        }

        @Override
        public void d(String tag, String msg) {
            Log.d(tag, msg);
        }

        @Override
        public void i(String tag, String msg) {
            Log.i(tag, msg);
        }

        @Override
        public void e(String tag, String msg) {
            Log.e(tag, msg);
        }

        @Override
        public void e(String tag, String msg, Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            Log.e(tag, msg + ":" + sw.toString());
        }

        @Override
        public void w(String tag, String msg) {
            Log.w(tag, msg);
        }
    }
}
