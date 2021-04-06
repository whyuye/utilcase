package com.wuhui.blocking;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {

        SynchronousQueue<Integer> noCapacityBlockingQueue = new SynchronousQueue<>();

        Thread producer = new Thread(() -> {
            try {
                noCapacityBlockingQueue.put(1); // 会被阻塞
                System.out.println("生产者生产完了数据");
            } catch (InterruptedException e) {
                System.out.println("生产者线程被中断");
            }
        }, "生产者");

        Thread consumer = new Thread(() -> {
            try {
                Integer take1 = noCapacityBlockingQueue.poll(); // peek这个会返回null，因为对于SynchronousQueue来说它是没有容量的
                System.out.println("1消费者取到了数据:" + take1);
                Integer take2 = noCapacityBlockingQueue.remove();// 消费完了poll就返回null了，如果数据不存在会抛出异常
                System.out.println("1消费者取到了数据:" + take2);
            } catch (Exception e) {
                System.out.println("消费者线程被中断" + e);  // java.util.NoSuchElementException(使用remove的时候，如果阻塞队列中没有数据会抛出异常)
            }
        }, "消费者");

        producer.start();
        // 让生产者线程先启动
        Thread.sleep(2000);
        consumer.start();
    }
}
