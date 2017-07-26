package com.github.yulechen.thread.executor;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.LockSupport;

import com.github.yulechen.thread.task.MyTask;


class ThreadPerTaskExecutor implements Executor {
    public void execute(Runnable r) {
        new Thread(r).start();
    }


    public static void main(String[] args) throws IOException {
        Executor executor = new ThreadPerTaskExecutor();
        executor.execute(new MyTask());
        executor.execute(new MyTask());
        executor.execute(new MyTask());
        LockSupport.park(); // block
    }
}