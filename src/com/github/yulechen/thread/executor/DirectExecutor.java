package com.github.yulechen.thread.executor;

import java.util.concurrent.Executor;

import com.github.yulechen.thread.task.MyTask;


class DirectExecutor implements Executor {
    public void execute(Runnable r) {
        r.run();
    }


    public static void main(String[] args) {
        Executor executor = new DirectExecutor();
        executor.execute(new MyTask());
        
    }
}