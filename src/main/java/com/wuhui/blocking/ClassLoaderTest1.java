package com.wuhui.blocking;

public class ClassLoaderTest1 {

    public static void main(String[] args) {
        /*
        1.输出结果：
        Person personNumber is :1
        Person hello world.
        1
         */
        /*
        说明结果：调用静态变量或者静态方法会触发静态变量或者静态方法所在类的加载链接初始化过程
         */
        //System.out.println(Child.personNumber);

        /*
        2.输出结果：
        Person personNumber is :1
        Person hello world.
        Child childNumber is :2
        Child hello world.
        2
         */
        /*
        说明结果：子类初始化时会先初始化父类
         */
        System.out.println(Child.childNumber);
    }
}

class Person {

    protected static int personNumber = 1;

    static {
        System.out.println("Person personNumber is :" + personNumber);
        System.out.println("Person hello world.");
    }
}

class Child extends Person {

    public static int childNumber = 2;

    static {
        System.out.println("Child childNumber is :" + childNumber);
        System.out.println("Child hello world.");
    }
}