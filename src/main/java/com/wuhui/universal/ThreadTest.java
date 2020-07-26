package com.wuhui.universal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(() -> {
            while (true);
        });

        executorService.execute(() -> {
            synchronized (ThreadTest.class) {
                while (true);
            }
        });

        executorService.execute(() -> {
            synchronized (ThreadTest.class) {
                while (true);
            }
        });

        System.out.println("wait all thread end.....");
    }
}
