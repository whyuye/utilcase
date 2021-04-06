package com.wuhui.array;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ArrayTest1 {

    public static void main(String[] args) {
        System.out.println("start");
        List<Integer> ints = new LinkedList<>();
        ints.add(1);
        ints.add(2);

        // 遍历LinkedList使用迭代器或者增强for循环（实际上使用的也是迭代器）的性能更高，因为是通过next
        /*
        public E next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next; // 迭代器遍历LinkedList读性能高的原因
            next = next.next; // 迭代器遍历LinkedList读性能高的原因
            nextIndex++;
            return lastReturned.item;
        }
         */
        final Iterator<Integer> iterator = ints.iterator(); // ListItr
        System.out.println("end");
    }

    private static void dynamicParam(String... strings) {
        System.out.println(strings); // 居然不是null
        System.out.println(strings.length); // 居然不会报空指针异常
    }
}
