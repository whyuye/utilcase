package com.wuhui.reference;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class WeakHashMapTest {

    public static void main(String[] args) throws InterruptedException {
        WeakHashMap<String, Integer> weakHashMap = new WeakHashMap<>();

        String a = new String("a");
        weakHashMap.put(a, 1);
        weakHashMap.put(new String("b"), 2);
        weakHashMap.put(new String("c"), 3);

        System.out.println("------演示WeakHashMap----------");
        System.out.println("------gc前-------");
        System.out.println(weakHashMap.size()); // 3

        a = null; // 让gc回收a指向的内存
        System.gc();

        // 保证gc执行
        TimeUnit.SECONDS.sleep(3);
        System.out.println("------gc后-------");
        System.out.println(weakHashMap.size()); // 0

        System.out.println("-----------------");
        System.out.println("------演示WeakReference----------");

        // WeakReference weakReference = new WeakReference("hello"); // 字符串常量不会被回收
        // WeakReference weakReference = new WeakReference(1); // 基本类型也不会被回收
        // WeakReference weakReference = new WeakReference(new String("hello")); // 引用类型就会被回收
        WeakReference weakReference = new WeakReference(new Integer(1)); // 引用类型就会被回收

        System.out.println("------gc前-------");
        System.out.println(weakReference.get());

        System.gc();
        // 保证让gc先回收
        TimeUnit.SECONDS.sleep(1);

        System.out.println("------gc后-------");
        System.out.println(weakReference.get());
    }
}
