package com.wuhui.reflect;

import lombok.Getter;
import lombok.Setter;

public class ReflectTest3 {

    public static void main(String[] args) {
        // Fields
        System.out.println("SuperClass info:");
        System.out.println(SuperClass.class.getClassLoader()); // app
        System.out.println(SuperClass.class.getDeclaredFields().length); // 3
        System.out.println(SuperClass.class.getFields().length); // 1
        System.out.println("-------------------");
        System.out.println("ChildClass info:");
        System.out.println(ChildClass.class.getClassLoader()); //app
        System.out.println(ChildClass.class.getDeclaredFields().length); // 2 返回当前类所有访问权限的字段
        System.out.println(ChildClass.class.getFields().length); // 2 返回当前类和继承类的public字段
        System.out.println("======end======");
    }
}

@Getter
@Setter
class SuperClass {

    private String name;

    public String name2;

    protected String extendedName;
}

@Getter
@Setter
class ChildClass extends SuperClass {

    private String childName;

    public String childName2;
    /**
     * 静态代码块只在 类加载器对这个类进行初始化的时候被进行(加载、链接、初始化)
     */
    static {
        System.out.println("i am ChildClass");
    }
}