package com.javaer.plusxposed.util;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import com.javaer.plusxposed.log.Vlog;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import de.robv.android.xposed.XC_MethodHook;

public class Util {

    /**
     * 打印hook函数的所有参数
     * @param param param
     * @param labelMessage label
     */
    public static void log(XC_MethodHook.MethodHookParam param, String labelMessage){

        Object[] paramArgs = param.args;
        if (paramArgs == null || paramArgs.length <= 0){
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("==============================\n");
        for (Object o : paramArgs){
            builder.append(labelMessage).append("=====>").append(o).append("\n");
        }
        builder.append("==============================");
        Vlog.log(builder.toString());

    }

    /**
     * 打印对象的所有Field
     * @param object object
     */
    public static void log(Object object) {
        if (object == null){
            Vlog.log("对象为空");
            return;
        }
        StringBuilder builder = new StringBuilder();
        Class class_obj = object.getClass();
        builder.append("============================\n");
        builder.append(class_obj.getName()+"\n");
        builder.append("============================\n");
        Field[] fields = class_obj.getDeclaredFields();
        for (Field f : fields){
            f.setAccessible(true);
            try {
                Object o = f.get(object);
                builder.append(f.getName() + ":" + o + "\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Vlog.log(builder.toString());
    }

    /**
     * 打印某对象当前类，以及所有父类域
     * @param object object
     */
    public static void logSuperFields(Object object){
        if (object == null){
            Vlog.log("对象为空");
            return;
        }
        StringBuilder builder = new StringBuilder();
        Class cls = object.getClass();
        builder.append("============================\n");
        builder.append(cls.getName()+"\n");
        builder.append("============================\n");
        Field[] fields = cls.getDeclaredFields();

        for (Field f : fields){
            f.setAccessible(true);
            try {
                Object o = f.get(object);
                builder.append(f.getName() + ":" + o + "\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            cls = cls.getSuperclass();
            if (cls == null || cls.equals(Object.class))
                break;
            try {
                fields = cls.getDeclaredFields();
                builder.append("super==============================>\n");
                for (Field f : fields){
                    f.setAccessible(true);
                    try {
                        Object o = f.get(object);
                        builder.append(f.getName() + ":" + o + "\n");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Throwable ignored) {

            }
        }
        Vlog.log(builder.toString());
    }

    /**
     * 获取对象的所有Field String
     * @param object object
     */
    private static String getObjectFieldsString(Object object) {
        if (object == null){
            Vlog.log("对象为空");
            return "";
        }
        StringBuilder builder = new StringBuilder();
        Class class_obj = object.getClass();
        builder.append("============================\n");
        builder.append(class_obj.getName()+"\n");
        builder.append("============================\n");
        Field[] fields = class_obj.getDeclaredFields();
        for (Field f : fields){
            f.setAccessible(true);
            try {
                Object o = f.get(object);
                builder.append(f.getName() + ":" + o + "\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }

    /**
     * 打印Map
     * @param map map
     */
    public static void log(Map map){
        if (map == null){
            Vlog.log("map is null");
            return;
        }
        if (map.size() <= 0){
            Vlog.log("map size is 0");
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (Object o : map.keySet()){
            Object value = map.get(o);
            builder.append(getObjectFieldsString(value)).append("\n");
        }
        Vlog.log(builder.toString());
    }

    /**
     * 打印Intent的键值对
     * @param intent intent
     */
    public static void log(Intent intent){
        if (intent == null){
            Vlog.log("intent is null");
            return;
        }
        Bundle bundle = intent.getExtras();
        if (bundle == null){
            Vlog.log("bundle is null");
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("==============================\n");
        for (String k : bundle.keySet()){
            builder.append(k + "=====>" + bundle.get(k) + "\n");
        }
        builder.append("==============================");
        String result = builder.toString();
        Vlog.log(result);
    }


    /**
     * 打印ContentValues的所有键值对
     * @param values values
     */
    public static void log(ContentValues values){
        if (values == null){
            Vlog.log("values is null");
            return;
        }
        if (values.size() <= 0){
            Vlog.log("values size is null");
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("ContentValues:\n");
        builder.append("==============================\n");
        for (String str : values.keySet()){
            builder.append(str).append(":").append(values.get(str)).append("\n");
        }
        builder.append("==============================");
        Vlog.log(builder.toString());
    }

    /**
     * 打印列表
     * @param list list
     */
    public static void log(List<Object> list){
        if (list == null || list.size() <= 0){
            Vlog.log("列表为空");
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("==============================\n");
        for (Object object : list){
            builder.append(getObjectFieldsString(object)).append("\n");
        }
        builder.append("==============================");
        Vlog.log(builder.toString());
    }


}
