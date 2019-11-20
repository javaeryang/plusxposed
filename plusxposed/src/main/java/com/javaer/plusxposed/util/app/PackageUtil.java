package com.javaer.plusxposed.util.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class PackageUtil {


    /**
     * 获取apk包的信息：版本号，名称，图标等
     * @param absPath apk包的绝对路径
     * @param context context
     */
    public static int apkVersion(String absPath, Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo = pm.getPackageArchiveInfo(absPath, PackageManager.GET_ACTIVITIES);
        if (pkgInfo != null) {
            ApplicationInfo appInfo = pkgInfo.applicationInfo;
            /* 必须加这两句，不然下面icon获取是default icon而不是应用包的icon */
            appInfo.sourceDir = absPath;
            appInfo.publicSourceDir = absPath;
//            String appName = pm.getApplicationLabel(appInfo).toString();// 得到应用名 
//            String packageName = appInfo.packageName; // 得到包名 
//            String version = pkgInfo.versionName; // 得到版本信息 
            int versionCode = pkgInfo.versionCode;

            return versionCode;
        }
        return 0;
    }

    /**
     * 启动应用
     * @param appPackageName 包名
     * @param context context
     */
    public static void startAPP(String appPackageName, Context context){
        try{
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(appPackageName);
            context.startActivity(intent);
        }catch(Throwable e){
            e.printStackTrace();
        }
    }
}
