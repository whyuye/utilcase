package com.wuhui.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest2 {

    static final class ThreadPerTaskExecutor implements Executor {
        public void execute(Runnable r) { new Thread(r).start(); }
    }

    public static void main(String[] args) {
        System.out.println("main线程是不是守护线程:" + Thread.currentThread().isDaemon()); // false
        // 由于main线程不是守护线程，那么在main中创建的线程也不是守护线程

        System.out.println("一个任务会创建一个线程来执行任务，开始执行：");
        ThreadPerTaskExecutor threadPerTaskExecutor = new ThreadPerTaskExecutor();

        threadPerTaskExecutor.execute(() -> {
            System.out.println("线程1");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPerTaskExecutor.execute(() -> {
            System.out.println("线程2");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPerTaskExecutor.execute(() -> {
            System.out.println("线程3");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPerTaskExecutor.execute(() -> {
            System.out.println("线程4");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
