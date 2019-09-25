package com.javaer.plusxposed.plugin.base;

import com.javaer.plusxposed.log.Vlog;

import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public abstract class BasePlugin {

    private Set<Object> unhooks = new HashSet<>();

    private XC_LoadPackage.LoadPackageParam packageParam;

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


}
