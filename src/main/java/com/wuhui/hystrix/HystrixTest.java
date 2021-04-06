package com.wuhui.hystrix;

import rx.Observer;

import java.util.concurrent.TimeUnit;

public class HystrixTest {

    public static void main(String[] args) {
        //注册观察者事件拦截
        final rx.Observable<String> fs = new HelloWorldCommand("World").toObservable();

        // toObservable的话，run是什么时候执行的? 第一次订阅吗？
        // 结论toObservable是第一次订阅后执行。后续的订阅还是可以收到已执行完成的结果的
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException exception) {
//            exception.printStackTrace();
//        }
        //注册结果回调事件 subsribe并不会阻塞当前线程。默认超时为1s
        System.out.println("第一次subscribe");
        fs.subscribe(result -> {
            //执行结果处理,result 为HelloWorldCommand返回的结果
            //用户对结果做二次处理.
            System.out.println(result);
            System.out.println("action call, thread name:" + Thread.currentThread().getName());
        });

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException exception) {
//            exception.printStackTrace();
//        }

        System.out.println("第二次subscribe start");
        //注册完整执行生命周期事件
        fs.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                // onNext/onError完成之后最后回调
                System.out.println("execute onCompleted");
                System.out.println("execute onCompleted, thread name:" + Thread.currentThread().getName());
            }
            @Override
            public void onError(Throwable e) {
                // 当产生异常时回调
                System.out.println("onError " + e.getMessage());
                e.printStackTrace();
            }
            @Override
            public void onNext(String v) {
                // 获取结果后回调
                System.out.println("onNext: " + v);
                System.out.println("onNext, thread name:" + Thread.currentThread().getName());
            }
        });

        // hystrixCommand的run已经执行完成了，再订阅一次，看看是否还会回调call
        System.out.println("第三次subscribe start");
        fs.subscribe(result -> {
            //执行结果处理,result 为HelloWorldCommand返回的结果
            //用户对结果做二次处理.
            System.out.println(result);
            System.out.println("action call, thread name:" + Thread.currentThread().getName());
        });

        // 睡眠主线程
        System.out.println("main sleep start");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println("main sleep end");
    }
}
