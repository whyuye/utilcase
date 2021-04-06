package com.wuhui.runtime;

import java.util.concurrent.TimeUnit;

public class ShutdownHook {

    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("钩子1开始");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println("钩子1结束");
        }));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("钩子2开始");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println("钩子2结束");
        }));

        // 等待钩子函数准备好
        TimeUnit.SECONDS.sleep(1);
        System.out.println("钩子函数已经准备，结束进程触发钩子函数执行");
        // 如果现象是钩子1开始和钩子2开始一期打印，则可以说明CPU可以同时调度这两个钩子线程
    }
}
