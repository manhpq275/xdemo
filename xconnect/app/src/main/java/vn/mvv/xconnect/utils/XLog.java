package vn.mvv.xconnect.utils;

import android.util.Log;

/**
 * Created by phuc.nguyen on 5/18/2016.
 */
public class XLog {
    private static final boolean DEBUG = true;
    public static void e(Class<?> clazz, String msg) {
        if (DEBUG)
            e(clazz.getSimpleName(), msg);
    }

    public static void d(Class<?> clazz, String msg) {
        if (DEBUG)
            d(clazz.getSimpleName(), msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG)
            Log.e(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG)
            Log.d(tag, msg);
    }

    public static void e(String msg) {
        if (DEBUG)
            d("XConnect", msg);
    }

    public static void d(String msg) {
        if (DEBUG)
            d("XConnect", msg);
    }
}
