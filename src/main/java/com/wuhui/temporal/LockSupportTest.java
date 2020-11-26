package com.wuhui.temporal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 *
 * 关于LockSupport
 */
public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {

        Thread parkThread = new Thread(() -> {
            System.out.println("park-thread start1");
            LockSupport.park(); // 看来park方法不会去清除中断标识，但是遇到中断标识为true的时候会马上返回
            System.out.println(Thread.currentThread().isInterrupted());
            System.out.println(Thread.interrupted()); // 清除中断标识
            System.out.println("park-thread start2");
            LockSupport.park();
            System.out.println(Thread.currentThread().isInterrupted());
            System.out.println("park-thread end");
        }, "park-thread");

//        Thread unParkThread = new Thread(() -> {
//            System.out.println("un-park-thread start");
//            LockSupport.unpark(parkThread);
//        }, "un-park-thread");

        parkThread.start();
        // 保证让park线程先执行
        TimeUnit.SECONDS.sleep(1);
        // unParkThread.start();
        parkThread.interrupt(); // 打断park线程后，如果线程后续还在park的话还是不能被park住

        // 等待所有子线程执行完成
        parkThread.join();
//        unParkThread.join();

        System.out.println("子线程全部执行完毕，退出主线程");
    }
}
