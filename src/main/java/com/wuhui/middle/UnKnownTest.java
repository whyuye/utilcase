package com.wuhui.middle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UnKnownTest {

    public static void main(String[] args) {
        keng1();
        keng2();
        keng3();
        keng4();
    }

    private static void keng1() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(i);
            set.remove(i-1);
        }
        System.out.println("keng1 result:" + set.size());
    }

    private static void keng2() {
        Set<Short> set = new HashSet<>();
        for (short i = 0; i < 100; i++) {
            set.add(i);;
            set.remove((short)(i - 1));
        }
        System.out.println("keng2 result:" + set.size());
    }

    private static void keng3() {
        Object i = 1 == 1 ? new Integer(3) : new Float(1);
        System.out.println("keng3 result:" + i); // 3.0
    }

    private static void keng4() {
        Map<Long, String> simpleMap = new HashMap<>();

        simpleMap.put(1L, "hello");
        simpleMap.put(2L, "world");

        Long[] keyArray = simpleMap.keySet().toArray(new Long[1]);

        System.out.println(keyArray.length);
    }
}
