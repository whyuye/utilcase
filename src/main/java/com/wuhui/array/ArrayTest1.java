package com.wuhui.array;

public class ArrayTest1 {

    public static void main(String[] args) {
        dynamicParam();
    }

    private static void dynamicParam(String... strings) {
        System.out.println(strings); // 居然不是null
        System.out.println(strings.length); // 居然不会报空指针异常
    }
}
