package com.javaer.plusxposed.execute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlusExecute {

    /**
     * 线程池20
     */
    private static ExecutorService fixedThreadPool;

    /**
     * 获取单例
     * @return 线程池
     */
    private static ExecutorService getFixedExecutorService(){
        if (fixedThreadPool == null){
            fixedThreadPool = Executors.newFixedThreadPool(20);
        }
        return fixedThreadPool;
    }

    /**
     * 执行任务
     * @param runnable runnable
     */
    public static void exexute(Runnable runnable){
        getFixedExecutorService().execute(runnable);
    }
}
