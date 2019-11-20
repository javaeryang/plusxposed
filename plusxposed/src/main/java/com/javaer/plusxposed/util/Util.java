package com.javaer.plusxposed.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Util {
    /**
     * 打开浏览器
     * @param context context
     * @param url url
     */
    public static void gotoBrowser(Context context, String url){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(intent, "选择浏览器"));
        }else {
            context.startActivity(intent);
        }
    }
}
