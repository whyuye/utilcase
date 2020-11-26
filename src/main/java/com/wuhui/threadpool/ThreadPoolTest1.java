package com.wuhui.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest1 {

    public static void main(String[] args) {
        Integer coreSize = 1;
        // Integer maxSize = 1;
        Integer maxSize = 3;
        Integer queueSize = 0;
        ThreadPoolSimpleProperties.ThreadPoolSimplePropertiesBuilder  builder =
                new ThreadPoolSimpleProperties.ThreadPoolSimplePropertiesBuilder(coreSize, maxSize, queueSize);
        builder.alive(0L);
        builder.aliveTimeUnit(TimeUnit.MILLISECONDS);
        Executor executor = getExecutor(builder.build());

        executor.execute(() -> {
            System.out.println("start 1");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            System.out.println("start 2");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            System.out.println("start 3");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 阻塞队列使用的是SynchronousQueue，并且最大线程数是0，看看会不会出现异常(会的)
        // 使用了阻塞队列SynchronousQueue的话，如果当前没有可用的线程用来执行提交的任务的话，会直接走拒绝策略
    }

    private static Executor getExecutor(ThreadPoolSimpleProperties threadPoolSimpleProperties) {
        return new ThreadPoolExecutor(threadPoolSimpleProperties.coreSize, threadPoolSimpleProperties.maxSize,
                threadPoolSimpleProperties.alive, threadPoolSimpleProperties.aliveTimeUnit, new SynchronousQueue<>(),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    static class ThreadPoolSimpleProperties {

        private final Integer coreSize;
        private final Integer maxSize;
        private final Integer queueSize;

        // optional
        private Long alive;
        private TimeUnit aliveTimeUnit;

        private ThreadPoolSimpleProperties(ThreadPoolSimplePropertiesBuilder builder) {
            if (builder.coreSize == null || builder.maxSize == null || builder.queueSize == null) {
                throw new IllegalArgumentException("必选参数不可以为空");
            }

            this.coreSize = builder.coreSize;
            this.maxSize = builder.maxSize;
            this.queueSize = builder.queueSize;
            this.alive = builder.alive;
            this.aliveTimeUnit = builder.aliveTimeUnit;
        }

        /**
         * 线程参数建造者
         */
        static class ThreadPoolSimplePropertiesBuilder {

            // need
            private final Integer coreSize;
            private final Integer maxSize;
            private final Integer queueSize;

            // optional
            private Long alive;
            private TimeUnit aliveTimeUnit;

            public ThreadPoolSimplePropertiesBuilder(Integer coreSize, Integer maxSize, Integer quueSize) {
                this.coreSize = coreSize;
                this.maxSize = maxSize;
                this.queueSize = quueSize;
            }

            public ThreadPoolSimplePropertiesBuilder alive(Long alive) {
                this.alive = alive;
                return this;
            }

            public ThreadPoolSimplePropertiesBuilder aliveTimeUnit(TimeUnit aliveTimeUnit) {
                this.aliveTimeUnit = aliveTimeUnit;
                return this;
            }

            public ThreadPoolSimpleProperties build() {
                return new ThreadPoolSimpleProperties(this);
            }
        }
    }
}
