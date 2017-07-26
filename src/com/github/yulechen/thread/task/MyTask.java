package com.github.yulechen.thread.task;

public class MyTask implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " task run.");

    }

}
