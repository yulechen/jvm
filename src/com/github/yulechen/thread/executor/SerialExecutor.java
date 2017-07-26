package com.github.yulechen.thread.executor;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.LockSupport;

import com.github.yulechen.thread.task.MyTask;


class SerialExecutor implements Executor {
    final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
    final Executor executor;
    Runnable active;


    SerialExecutor(Executor executor) {
        this.executor = executor;
    }


    public synchronized void execute(final Runnable r) {
        tasks.offer(new Runnable() {
            public void run() {
                try {
                    r.run();
                }
                finally {
                    scheduleNext();
                }
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }


    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }


    public static void main(String[] args) {
        Executor directExecutor = new ThreadPerTaskExecutor();
        Executor executor = new SerialExecutor(directExecutor);
        executor.execute(new MyTask());
        executor.execute(new MyTask());
        executor.execute(new MyTask());
        LockSupport.park();

    }
}