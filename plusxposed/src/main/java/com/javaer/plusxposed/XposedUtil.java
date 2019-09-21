package com.javaer.plusxposed;

import com.javaer.plusxposed.log.Vlog;

import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedUtil {

    private static XC_LoadPackage.LoadPackageParam loadPackageParam = null;

    private static Set<XC_MethodHook.Unhook> unhookSet = new HashSet<>();

    public static void setLoadPackageParam(XC_LoadPackage.LoadPackageParam Param){
        if (loadPackageParam == null){
            loadPackageParam = Param;
        }
    }

    public static Class<?> findClass(String className){
        try {
            if (loadPackageParam == null){
                throw new Throwable("You hasn't set loadPackageParam");
            }
            return XposedHelpers.findClass(className, loadPackageParam.classLoader);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
        return null;
    }

    public static void hookMethod(String className, String methodName, Object... paramAndCallBack) throws Throwable {
        if (loadPackageParam == null){
            Vlog.log(new Throwable("You hasn't set loadPackageParam"));
            return;
        }
        try {
            XC_MethodHook.Unhook unhook = XposedHelpers.findAndHookMethod(className, loadPackageParam.classLoader, methodName, paramAndCallBack);
            unhookSet.add(unhook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public static void hookMethod(Class className, String methodName, Object... paramAndCallBack) throws Throwable {
        if (loadPackageParam == null){
            Vlog.log(new Throwable("You hasn't set loadPackageParam"));
            return;
        }
        try {
            XC_MethodHook.Unhook unhook = XposedHelpers.findAndHookMethod(className, methodName, paramAndCallBack);
            unhookSet.add(unhook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public static void hookConstructor(Class className, Object... paramAndCallBack){
        try {
            if (loadPackageParam == null){
                throw new Throwable("You hasn't set loadPackageParam");
            }
            XC_MethodHook.Unhook unhook = XposedHelpers.findAndHookConstructor(className, paramAndCallBack);
            unhookSet.add(unhook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public static void hookConstructor(String className, Object... paramAndCallBack){
        try {
            if (loadPackageParam == null){
                throw new Throwable("You hasn't set loadPackageParam");
            }
            Class cls = XposedUtil.findClass(className);
            hookConstructor(cls, paramAndCallBack);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public static void unHook(){
        if (unhookSet.size() > 0){
            for (XC_MethodHook.Unhook unhook : unhookSet){
                unhook.unhook();
            }
        }
    }
}
