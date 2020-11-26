package com.wuhui.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest3 {

    public static void main(String[] args) throws InterruptedException {
//        // ArrayBlockingQueue的容量不允许小于等于0
//        ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(1, 1 , 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(0));
//        threadPoolExecutor1.submit(() -> System.out.println("helloworld1"));
//
//        // LinkedBlockingQueue的容量不允许小于等于0
//        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(1, 1 , 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(0));
//        threadPoolExecutor2.submit(() -> System.out.println("helloworld2"));

        //
        ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(1, 1 , 0, TimeUnit.MILLISECONDS, new LinkedTransferQueue<>());
        // threadPoolExecutor3.submit(() -> System.out.println("helloworld3"));
        threadPoolExecutor3.getQueue().put(() -> { // 线程都还没被new出来，导致了如果直接把任务放到队列的话并不会启动线程。也验证了一点，当队列没有满的时候
            System.out.println("helloworld3");
        });

        TimeUnit.SECONDS.sleep(1);
    }
}
