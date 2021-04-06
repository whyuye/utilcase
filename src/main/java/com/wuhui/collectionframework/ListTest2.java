package com.wuhui.collectionframework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ListTest2 {

    public static void main(String[] args) {
        // 限制不扩容，避免影响测试结果
        int list1Capacity = 200;
        List<Integer> list1 = new ArrayList<>(list1Capacity);
        initIntegerList(list1, 10);

        int list2Capacity = 100;
        List<Integer > list2 = new ArrayList<>(list2Capacity);
        initIntegerList(list2, 100);

        // 判断使用add快还是使用addAll快, 用纳秒统计
        // 只添加一个元素的话，因为addAll需要涉及数组的拷贝，在耗时上addAll会更高
        // 如果是添加多个元素，看看谁的性能更高呢？当数据变很多时，addAll只需要拷贝一次内存，性能上会更高
        addByFor(list1, list2, TimeUnit.NANOSECONDS);
        addAll(list1, list2, TimeUnit.NANOSECONDS);

        // 判断使用add快还是使用addAll快, 用毫秒统计
//        addByFor(list1, list2, TimeUnit.MILLISECONDS);
//        addAll(list1, list2, TimeUnit.MILLISECONDS);
    }

    private static void initIntegerArray(Integer... integers) {

    }

    private static void initIntegerList(List<Integer> list1, int count) {
        for (int i = 0; i < count; i++) {
            list1.add(i);
        }
    }

    private static void addByFor(List<Integer> list1, List<Integer> list2) {
        long startNanoTime = System.nanoTime();

        for (int i = 0; i < list2.size(); i++) {
            list1.add(list2.get(i));
        }

        long endNanoTime = System.nanoTime();

        System.out.println("addByFor耗时: " + (endNanoTime - startNanoTime));
    }

    private static void addAll(List<Integer> list1, List<Integer> list2, TimeUnit timeUnit) {
        long startTime = TimeUnit.MILLISECONDS.equals(timeUnit) ? System.currentTimeMillis() : System.nanoTime();

        list1.addAll(list2);

        long endTime = TimeUnit.MILLISECONDS.equals(timeUnit) ? System.currentTimeMillis() : System.nanoTime();

        System.out.println("addAll耗时: " + (endTime - startTime));
    }

    private static void addByFor(List<Integer> list1, List<Integer> list2, TimeUnit timeUnit) {
        long startTime = TimeUnit.MILLISECONDS.equals(timeUnit) ? System.currentTimeMillis() : System.nanoTime();

        for (int i = 0; i < list2.size(); i++) {
            list1.add(list2.get(i));
        }

        long endTime = TimeUnit.MILLISECONDS.equals(timeUnit) ? System.currentTimeMillis() : System.nanoTime();

        System.out.println("addByFor耗时: " + (endTime - startTime));
    }
}
