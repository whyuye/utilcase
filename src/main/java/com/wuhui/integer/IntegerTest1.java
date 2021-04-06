package com.wuhui.integer;

public class IntegerTest1 {

    public static void main(String[] args) {
        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b); // true

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d); // false

        Integer e = new Integer(127);
        System.out.println(a == e); // false

        int f = 128;
        System.out.println(c == f); // true。对c进行自动拆箱
    }
}
