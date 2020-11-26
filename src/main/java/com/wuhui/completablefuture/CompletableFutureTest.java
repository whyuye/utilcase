package com.wuhui.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println("helloworld");
            System.out.println(Thread.currentThread().getName());

            throw new RuntimeException("exception");
        }, executor).thenAcceptAsync(a1 -> {
            System.out.println("world love me1");
            System.out.println(Thread.currentThread().getName());
        }, executor).thenAccept((a2) -> {
            System.out.println("world love me2");
            System.out.println(Thread.currentThread().getName());
        }).exceptionally((e) -> { // exceptionally会把异常吞了？
            System.out.println("发生异常:" + e.getMessage());
            return null;
        });

        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException exception) {
                // ignore
            }
            System.out.println("helloworld");
            System.out.println(Thread.currentThread().getName());

            // throw new RuntimeException("exception");
        }, executor);


        Thread.sleep(1000);
        System.out.println("wait all thread end");
        try {
            CompletableFuture.allOf(completableFuture1, completableFuture2).join();
        } finally {
            System.out.println("todo 关闭线程池");
            executor.shutdown();
        }
    }
}
