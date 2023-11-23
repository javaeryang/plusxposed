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

public abstract class BasePlugin {

    private Set<Object> unhooks = new HashSet<>();

    private ClassLoader loader;

    public abstract String getName();

    public abstract void handleLoadPackage(ClassLoader classLoader);

    public void loadPackage(ClassLoader classLoader){

        this.loader = classLoader;
        handleLoadPackage(classLoader);
    }


    public void unHookPlugin(){
        if (unhooks == null || unhooks.isEmpty()){
            return;
        }
        for (Object object : unhooks){
            XposedHelpers.callMethod(object, getUnhook());
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

    public ClassLoader getClassloader(){
        try {
            if (loader == null){
                throw new Throwable("loader is null");
            }
            return loader;
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
        return null;
    }

    public Class<?> findClass(String className){
        try {
            if (loader == null){
                throw new Throwable("loader is null");
            }
            return XposedHelpers.findClass(className, loader);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
        return null;
    }

    public void hookMethod(String className, String methodName, Object... paramAndCallBack){
        if (loader == null){
            Vlog.log(new Throwable("You hasn't set loader"));
            return;
        }
        try {
            XC_MethodHook.Unhook unhook = XposedHelpers.findAndHookMethod(className, loader, methodName, paramAndCallBack);
            unhooks.add(unhook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public void hookMethod(Class<?> className, String methodName, Object... paramAndCallBack){
        if (loader == null){
            Vlog.log(new Throwable("You hasn't set loader"));
            return;
        }
        try {
            XC_MethodHook.Unhook unhook = XposedHelpers.findAndHookMethod(className, methodName, paramAndCallBack);
            unhooks.add(unhook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public void hookConstructor(Class<?> className, Object... paramAndCallBack){
        try {
            if (loader == null){
                throw new Throwable("You hasn't set loader");
            }
            XC_MethodHook.Unhook unhook = XposedHelpers.findAndHookConstructor(className, paramAndCallBack);
            unhooks.add(unhook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public void hookConstructor(String className, Object... paramAndCallBack){
        try {
            if (loader == null){
                throw new Throwable("You hasn't set loader");
            }
            Class<?> cls = XposedUtil.findClass(className);
            hookConstructor(cls, paramAndCallBack);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public void hookAllMethods(String className, String methodName, XC_MethodHook xcMethodHook){
        try {
            if (loader == null){
                throw new Throwable("You hasn't set loader");
            }
            Class<?> cls = findClass(className);

            if (cls == null){
                throw new Throwable("Class not found");
            }
            hookAllMethods(cls, methodName, xcMethodHook);
        }catch (Throwable throwable){
            Vlog.log(throwable);
        }
    }

    public void hookAllMethods(Class<?> className, String methodName, XC_MethodHook xcMethodHook){
        try {
            if (loader == null){
                throw new Throwable("You hasn't set loader");
            }
            Method[] methods = className.getDeclaredMethods();
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

    public void hookAllConstructors(Class<?> className, XC_MethodHook xcMethodHook){
        try {
            if (loader == null){
                throw new Throwable("You hasn't set loader");
            }
            Constructor<?>[] constructors = className.getDeclaredConstructors();
            if (constructors.length == 0){
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
            if (loader == null){
                throw new Throwable("You hasn't set loader");
            }
            Class<?> cls = findClass(className);
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
