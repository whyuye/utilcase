package com.wuhui.completablefuture;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutionCompleteServiceTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10); // 非守护线程

        ExecutorCompletionService<Object> completionService = new ExecutorCompletionService<>(executorService);

        completionService.submit(() -> {
            // 模拟调用延时
            TimeUnit.SECONDS.sleep(1);
            return "hello";
        });

        completionService.submit(() -> {
            // 模拟调用延时
            TimeUnit.SECONDS.sleep(4);
            return 123;
        });

        completionService.submit(() -> {
           // 模拟调用时间
           TimeUnit.SECONDS.sleep(2);
           throw new RuntimeException();
           //return new Person("boy", 18);
        });

        for (int i = 0; i < 3; i++) {
            try {
                convertFutureResult(completionService.take().get());
            } catch (InterruptedException exception) {
                // ignore
                System.out.println("interrupt:" + exception.getMessage());
            } catch (ExecutionException e) {
                System.out.println("submit exception" + e.getMessage());
            }
        }
        System.out.println("end");
        System.out.println("active thread count in threadpool:" + Thread.activeCount());
    }

    private static void convertFutureResult(Object o) {
        if (o instanceof Person) {
            System.out.println("Person:" + ((Person) o).getName());
        }
        if (o instanceof String) {
            System.out.println("String:" + o);
        }
        if (o instanceof Integer) {
            System.out.println("Integer:" + o);
        }
    }
}

@Data
@AllArgsConstructor
class Person {

    private String name;

    private Integer age;
}