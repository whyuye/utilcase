package com.wuhui.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock reentrantLock = new ReentrantLock();
        // Condition condition = reentrantLock.newCondition();
        // throw Exception
        // condition.await();

        Thread a = new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("a start work");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("a end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }, "a");

        Thread b = new Thread(() -> {
            try {
                long start = System.currentTimeMillis();
                System.out.println("b wait");
                reentrantLock.tryLock(10, TimeUnit.SECONDS);
                long end = System.currentTimeMillis();
                System.out.println("应该在1s后能拿到锁");
                System.out.println("b end");
                System.out.println(end - start);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }, "b");

        a.start();

        // 保证让a线程先获取到锁
        TimeUnit.SECONDS.sleep(1);

        b.start();

        // 等待线程执行完成
        a.join();
        b.join();

        System.out.println("all thread end");
    }
}
