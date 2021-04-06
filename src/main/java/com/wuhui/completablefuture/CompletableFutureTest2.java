package com.wuhui.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest2 {

    public static void main(String[] args) {

        Thread main = Thread.currentThread();

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            //try {
            // ForkJoinPool.commonPool-worker-1,is deamon :true。居然是守护线程，这个是我没有想到的
            System.out.println(Thread.currentThread().getName() + "," +
                    "is deamon :" + Thread.currentThread().isDaemon());
            System.out.println("任务开始执行");
            //throw new RuntimeException();
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException exception) {
//                exception.printStackTrace();
//            }
            //
        });

        // 非阻塞。注册完成回调
        completableFuture.whenComplete((v, e) -> {
            System.out.println("thread name:" + Thread.currentThread().getName());
            System.out.println("任务执行结束, 返回值:" + v + e.getMessage());
            System.out.println("任务执行结束, 异常信息:" + e.getMessage());
            System.out.println("thread name:" + Thread.currentThread().getName());
            main.interrupt();
        });

        System.out.println(11111);
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.out.println("主线程被中断");
        }
        System.out.println("主线程执行完毕");
    }
}
