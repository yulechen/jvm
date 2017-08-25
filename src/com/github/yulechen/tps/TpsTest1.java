package com.github.yulechen.tps;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * 1秒=1000毫秒 <br/>
 * 1毫秒=1000微秒<br/>
 * 1微秒=1000纳秒 <br/>
 * 1纳秒=1000皮秒 <br/>
 * 1皮秒=1000飞秒 <br/>
 * 个人电脑的微处理器执行一道指令（如将两数相加）约需2至4纳秒
 * 
 * @author Huoyunren
 *
 */
public class TpsTest1 {

    public static int number = 0;

    static Executor pool = Executors.newFixedThreadPool(1);
    public static int MAX_COUNT = 10000000;// 10000w


    public static void main(String[] args) {
        // long start = System.nanoTime();// 纳秒
        //
        // for (int i = 0; i < 100000; i++) {
        // operate();
        // }
        long start = System.currentTimeMillis();
        while (true) {
            pool.execute(new Task(start));
        }
    }


    public static void operate() {
        number = number + 1;
    }

    // (1qw)thread 1 -->297 ms 3209
    // (1qw)thread 2 -->1477 ms
    // (1qw)thread 4 -->435
    // (1qw)thread 8 -->8737(1qw)
    static class Task implements Runnable {
        long start = 0l;


        public Task(long start) {
            this.start = start;
        }


        // 1.5s
        @Override
        public void run() {
            operate();
            if (number == MAX_COUNT) {
                System.out.println("time" + (System.currentTimeMillis() - start));
            }
        }
    }

}
