package com.wuhui.reflect;

public class ReflectTest4 {

    public static void main(String[] args) {
        System.out.println(SuperClassTest.class.isInstance(new normalClassTest())); // false, 继承关系居然是false
        System.out.println(SuperInterfaceTest.class.isInstance(new normalClassTest())); // true
        System.out.println(normalClassTest.class.isInstance(new normalClassTest())); // true
    }
}

abstract class SuperClassTest {

}

interface SuperInterfaceTest {

}

class normalClassTest extends SuperClass implements SuperInterfaceTest {

}