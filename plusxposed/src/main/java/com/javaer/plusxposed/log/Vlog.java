package com.javaer.plusxposed.log;

import android.util.Log;

public class Vlog {
    private static String TAG = "plus-xposed";

    public static void setTag(String tag){
        TAG = tag;
    }

    public static void log(String message){
        Log.i(TAG, message);
    }

    public static void log(String msg, Throwable throwable){
        Log.e(TAG, msg, throwable);
    }

    public static void log(Throwable throwable){
        log("get an error exception", throwable);
    }

}
