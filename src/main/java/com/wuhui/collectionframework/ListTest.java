package com.wuhui.collectionframework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("a");
        list1.add("b");
        list1.add("b");
        list1.add("c");
        list1.add("c");
        removeByIterator(list1);// 删除指定的"b"元素
        System.out.println("list1:" + list1);

        System.out.println("======================");

        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("a");
        list2.add("b");
        list2.add("b");
        list2.add("c");
        list2.add("c");
        removeByForEach(list2);// 删除指定的"b"元素
        System.out.println("list2:" + list2);
    }

    private static void removeByIterator(List<String> list) {
        Iterator<String> it = list.iterator();

        while (it.hasNext()) {
            String str = it.next();

            if (str.equals("b")) {
                // 在迭代器内部实现remove时，会同时修改list的modCount成员变量和迭代器自身维护的expectedModCount
                it.remove();
            }
        }
    }

    private static void removeByForEach(List<String> list) {
        for (String s : list) {
            if (s.equals("b")) {
                // foreach是语法糖，java会转化成迭代器的方式遍历，在list内部维护这一个成员变量modCount，在迭代器内部维护这一个成员变量expectedModCount，
                // 这里调用的remove是list的remove，只会修改modCount，这就导致了下一次遍历，判断modCount != expectedModCount就抛出了ConcurrentModificationException异常
                list.remove(s);
            }
        }
    }
}
