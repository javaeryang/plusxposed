package com.javaer.plusxposed.util.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.text.TextUtils;

import com.javaer.plusxposed.log.Vlog;

public class ProxyCheckUtil {
    public static boolean isWifiProxy(Context context) {
        if (!isWifi(context)){
            return false;
        }
        String host;
        String port;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            host = System.getProperty("http.proxyHost");
            port = System.getProperty("http.proxyPort");
        } else {
            host = Proxy.getHost(context);
            port = String.valueOf(Proxy.getPort(context));
        }
        Vlog.log("host===>" + host + "===>port===>"+port);
        return !TextUtils.isEmpty(host) && !TextUtils.isEmpty(port);
    }

    public static boolean isWifi(Context context) {
        return getNetType(context) == 0;
    }

    public static String getNetTypeString(Context context) {
        if (!isNetworkConnected(context)) {
            return "NO_CONNECT";
        }
        if (isWifi(context)) {
            return "WIFI";
        }
        return "MOBILE";
    }

    @SuppressLint("MissingPermission")
    public static NetworkInfo Yl(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager == null) {
            return null;
        }
        try {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Throwable th) {
            Vlog.log(th);
        }
        return networkInfo;
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo Yl = Yl(context);
        if (Yl == null) {
            return false;
        }
        return Yl.isConnected();
    }

    public static int getNetType(Context context) {
        if (context == null) {
            return -1;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                return -1;
            }
            @SuppressLint("MissingPermission") NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return -1;
            }
            if (activeNetworkInfo.getType() == 1) {
                return 0;
            }
            if (activeNetworkInfo.getExtraInfo() != null) {
                if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("uninet")) {
                    return 1;
                }
                if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("uniwap")) {
                    return 2;
                }
                if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("3gwap")) {
                    return 3;
                }
                if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("3gnet")) {
                    return 4;
                }
                if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("cmwap")) {
                    return 5;
                }
                if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("cmnet")) {
                    return 6;
                }
                if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("ctwap")) {
                    return 7;
                }
                if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("ctnet")) {
                    return 8;
                }
                if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("LTE")) {
                    return 10;
                }
            }
            return 9;
        } catch (Throwable unused) {
            Vlog.log(unused);
            return -1;
        }
    }
}
