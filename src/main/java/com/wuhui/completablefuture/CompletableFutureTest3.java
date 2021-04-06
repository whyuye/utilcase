package com.wuhui.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest3 {

    public static void main(String[] args) {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            //sleepMillSecond(30000);
            // sleepNanoSecond(1);
            //System.out.println(Thread.currentThread().getName());
            return 1;
        });
        f1.whenComplete((i, throwable) -> {  // 如果supplyAsync已经执行完了，whenComplete会由当前线程执行
            sleepSecond(2);
            System.out.println("A");
        });
        System.out.println("B");
    }

    private static void sleepSecond(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleepMillSecond(int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleepNanoSecond(int i) {
        try {
            TimeUnit.NANOSECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        CompletableFuture<Integer> f1 = CompletableFuture.completedFuture(1);
//        f1.whenComplete((i, throwable) -> {
//            sleepSecond(2);
//            System.out.println("A");
//        });
//        System.out.println("B");
//    }
//
//    private static void sleepSecond(int i) {
//        try {
//            TimeUnit.SECONDS.sleep(i);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
