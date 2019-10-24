package com.javaer.plusxposed;

import com.javaer.plusxposed.log.Vlog;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedUtil {

    private static XC_LoadPackage.LoadPackageParam loadPackageParam = null;

    private static Set<Object> unhookSet = new HashSet<>();

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

    public static void hookMethod(String className, String methodName, Object... paramAndCallBack){
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

    public static void hookMethod(Class className, String methodName, Object... paramAndCallBack){
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

    public static void hookAllMethods(String className, String methodName, XC_MethodHook xcMethodHook){
        try {
            if (loadPackageParam == null){
                throw new Throwable("You hasn't set loadPackageParam");
            }
            Class cls = findClass(className);

            if (cls == null){
                throw new Throwable("Class not found");
            }
            XposedUtil.hookAllMethods(cls, methodName, xcMethodHook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public static void hookAllMethods(Class className, String methodName, XC_MethodHook xcMethodHook){
        try {
            if (loadPackageParam == null){
                throw new Throwable("You hasn't set loadPackageParam");
            }
            Method[] methods = className.getDeclaredMethods();
            if (methods == null){
                throw new Throwable(className.getCanonicalName() + " has 0 method");
            }
            for (Method m : methods){
                if (m.getName().equals(methodName)){
                    XC_MethodHook.Unhook unhook = XposedBridge.hookMethod(m, xcMethodHook);
                    unhookSet.add(unhook);
                }
            }
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public static void hookAllConstructors(Class className, XC_MethodHook xcMethodHook){
        try {
            if (loadPackageParam == null){
                throw new Throwable("You hasn't set loadPackageParam");
            }
            Constructor<?>[] constructors = className.getDeclaredConstructors();
            if (constructors == null || constructors.length <= 0){
                throw new Throwable(className.getCanonicalName() + " has 0 constructor");
            }
            for (Member m : constructors){
                XC_MethodHook.Unhook unhook = XposedBridge.hookMethod(m, xcMethodHook);
                unhookSet.add(unhook);
            }
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public static void hookAllConstructors(String className, XC_MethodHook xcMethodHook){
        try {
            if (loadPackageParam == null){
                throw new Throwable("You hasn't set loadPackageParam");
            }
            Class cls = findClass(className);
            if (cls == null){
                throw new Throwable("Class not found");
            }
            XposedUtil.hookAllConstructors(cls, xcMethodHook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public static void unHook(){
        if (unhookSet.size() > 0){
            for (Object unhook : unhookSet){
                XposedHelpers.callMethod(unhook, getUnhook());
            }
        }
    }

    private static String getUnhook(){
        return un() + hook();
    }

    private static String un(){
        return "un";
    }

    private static String hook(){
        return "hook";
    }
}
