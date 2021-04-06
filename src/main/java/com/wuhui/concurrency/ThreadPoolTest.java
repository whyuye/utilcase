package com.wuhui.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 验证拒绝策略的猜想
 *
 * @author wuhui
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = createSumDataThreadPoolExecutor();
        IntStream.range(0, 100).forEach(i -> {
            threadPoolExecutor.execute(() -> {
                System.out.println(i);
                if (i <= 20) {
                    try {
                        TimeUnit.SECONDS.sleep(5L);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + "interrupt");
                    }
                }
            });
        });
        System.out.println(threadPoolExecutor.getActiveCount());
        // 这边shutdown了，在队列中的任务还能执行。
        threadPoolExecutor.shutdown();
    }

    private static ThreadPoolExecutor createSumDataThreadPoolExecutor() {
        final int corePoolSize = Runtime.getRuntime().availableProcessors();
        final int maxPoolSize = Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(10),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
