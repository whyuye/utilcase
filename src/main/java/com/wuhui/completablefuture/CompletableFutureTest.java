package com.wuhui.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        });

        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            System.out.println("helloworld");
            System.out.println(Thread.currentThread().getName());

            // throw new RuntimeException("exception");
        }, executor);


        Thread.sleep(1000);
        try {
            CompletableFuture.allOf(completableFuture1, completableFuture2).join();
        } finally {
            System.out.println("todo 关闭线程池");
            executor.shutdown();
        }
    }
}
