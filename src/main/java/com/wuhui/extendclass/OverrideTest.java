package com.wuhui.extendclass;

public class OverrideTest {

    public static void main(String[] args) {
        SubClass object = new SubClass();
        object.method();
    }
}

class SuperClass {

    /**
     * 静态方法不可以被子类进行重写
     * 子类可以出现与父类相同的名称的静态方法，但是这个不等同于重写
     */
    public static void method() {
        System.out.println("SuperClass hello world");
    }
}

class SubClass extends SuperClass {

//    public static void method() {
//        System.out.println("SubClass hello world");
//    }
}
