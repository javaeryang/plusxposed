package com.javaer.plusxposed.plugin.base;

import com.javaer.plusxposed.XposedUtil;
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

public abstract class BasePlugin {

    private Set<Object> unhooks = new HashSet<>();

    private XC_LoadPackage.LoadPackageParam packageParam;

    public abstract String getName();

    public abstract void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam);

    public void loadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam){

        this.packageParam = loadPackageParam;
        handleLoadPackage(loadPackageParam);
    }


    public void unHookPlugin(){
        if (unhooks == null || unhooks.isEmpty()){
            return;
        }
        for (Object object : unhooks){
            XposedHelpers.callMethod(object, "unhook");
        }
    }

    public Class<?> findClass(String className){
        try {
            if (packageParam == null){
                throw new Throwable("packageParam is null");
            }
            return XposedHelpers.findClass(className, packageParam.classLoader);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
        return null;
    }

    public void hookMethod(String className, String methodName, Object... paramAndCallBack){
        if (packageParam == null){
            Vlog.log(new Throwable("You hasn't set loadPackageParam"));
            return;
        }
        try {
            XC_MethodHook.Unhook unhook = XposedHelpers.findAndHookMethod(className, packageParam.classLoader, methodName, paramAndCallBack);
            unhooks.add(unhook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public void hookMethod(Class className, String methodName, Object... paramAndCallBack){
        if (packageParam == null){
            Vlog.log(new Throwable("You hasn't set loadPackageParam"));
            return;
        }
        try {
            XC_MethodHook.Unhook unhook = XposedHelpers.findAndHookMethod(className, methodName, paramAndCallBack);
            unhooks.add(unhook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public void hookConstructor(Class className, Object... paramAndCallBack){
        try {
            if (packageParam == null){
                throw new Throwable("You hasn't set loadPackageParam");
            }
            XC_MethodHook.Unhook unhook = XposedHelpers.findAndHookConstructor(className, paramAndCallBack);
            unhooks.add(unhook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public void hookConstructor(String className, Object... paramAndCallBack){
        try {
            if (packageParam == null){
                throw new Throwable("You hasn't set loadPackageParam");
            }
            Class cls = XposedUtil.findClass(className);
            hookConstructor(cls, paramAndCallBack);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public void hookAllMethods(String className, String methodName, XC_MethodHook xcMethodHook){
        try {
            if (packageParam == null){
                throw new Throwable("You hasn't set loadPackageParam");
            }
            Class cls = findClass(className);

            if (cls == null){
                throw new Throwable("Class not found");
            }
            hookAllMethods(cls, methodName, xcMethodHook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public void hookAllMethods(Class className, String methodName, XC_MethodHook xcMethodHook){
        try {
            if (packageParam == null){
                throw new Throwable("You hasn't set loadPackageParam");
            }
            Method[] methods = className.getDeclaredMethods();
            if (methods == null){
                throw new Throwable(className.getCanonicalName() + " has 0 method");
            }
            for (Method m : methods){
                if (m.getName().equals(methodName)){
                    XC_MethodHook.Unhook unhook = XposedBridge.hookMethod(m, xcMethodHook);
                    unhooks.add(unhook);
                }
            }
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public void hookAllConstructors(Class className, XC_MethodHook xcMethodHook){
        try {
            if (packageParam == null){
                throw new Throwable("You hasn't set loadPackageParam");
            }
            Constructor<?>[] constructors = className.getDeclaredConstructors();
            if (constructors == null || constructors.length <= 0){
                throw new Throwable(className.getCanonicalName() + " has 0 constructor");
            }
            for (Member m : constructors){
                XC_MethodHook.Unhook unhook = XposedBridge.hookMethod(m, xcMethodHook);
                unhooks.add(unhook);
            }
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public void hookAllConstructors(String className, XC_MethodHook xcMethodHook){
        try {
            if (packageParam == null){
                throw new Throwable("You hasn't set loadPackageParam");
            }
            Class cls = findClass(className);
            if (cls == null){
                throw new Throwable("Class not found");
            }
            hookAllConstructors(cls, xcMethodHook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public Object invokeOriginalMethod(Member method, Object thisObject, Object[] args){
        try {
            return XposedBridge.invokeOriginalMethod(method, thisObject, args);
        } catch (Throwable e) {
            Vlog.log(e);
        }
        return null;
    }
}
