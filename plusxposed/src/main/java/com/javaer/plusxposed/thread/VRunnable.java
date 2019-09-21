package com.javaer.plusxposed.thread;


import com.javaer.plusxposed.log.Vlog;

/**
 * 无限循环任务
 */
public abstract class VRunnable implements Runnable {

    private boolean isStop = false;

    private VRunCallback vRunCallback;

    protected void setStop(){
        isStop = true;
    }

    protected void start(){
        isStop = false;
    }

    protected void setVRunCallback(VRunCallback vRunCallback){
        this.vRunCallback = vRunCallback;
    }

    @Override
    public void run() {
        while (true){
            if (isStop){
                if (vRunCallback != null){
                    vRunCallback.onStop();
                }
                break;
            }
            try {
                runTask();
            }catch (Throwable throwable){
                Vlog.log(throwable);
            }
        }
    }

    public abstract void runTask() throws Throwable;
}
