package com.wuhui.aqs;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class SemaPhoreTest {

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(0);
        //Executor executor1 = Executors.newFixedThreadPool(3);
        ExecutorService executor2 = Executors.newFixedThreadPool(3, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });

        // 提交第一个任务
        executor2.execute(() -> {
            System.out.println("第一个任务开始获取信号量3个");
            semaphore.acquireUninterruptibly(3);
            System.out.println("第一个任务获取信号量成功");
        });

        // 保证让第一个任务，先去获取信号量，没获取到进入aqs阻塞队列
        TimeUnit.SECONDS.sleep(1);

        // 提交第二个任务
        executor2.execute(() -> {
            System.out.println("第二个任务开始获取信号量1个");
            semaphore.acquireUninterruptibly(1);
            System.out.println("第二个任务获取信号量成功");
        });

        // 保证让第一个任务，和第二任务都进入到aqs阻塞队列
        TimeUnit.SECONDS.sleep(1);

        // 放入一个信号量，看看结果，并不会唤醒第二个任务
        //semaphore.release();
        // 放入三个信号量，看看结果，成功唤醒先进入到阻塞队列的任务
        semaphore.release(3);

        // 关闭线程池
        executor2.shutdown();
    }
}
