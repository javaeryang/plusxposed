package com.javaer.plusxposed.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    /**
     * 短提示
     * @param context context
     * @param str str
     */
    public static void ToastShort(Context context, String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长提示
     * @param context context
     * @param str str
     */
    public static void ToastLong(Context context, String str){
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}
