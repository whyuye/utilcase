package com.wuhui.reflect;

import lombok.Data;

import java.lang.reflect.Field;

public class ReflectTest5 {
    public static void main(String[] args) {
        final Field[] declaredFields1 = Person.class.getDeclaredFields();
        for (Field field : declaredFields1) {
            System.out.println(field.getName() + " is Synthetic:" + field.isSynthetic());
        }

        System.out.println("=================================");

        final Field[] declaredFields2 = Person.Child.class.getDeclaredFields();
        for (Field field : declaredFields2) {
            System.out.println(field.getName() + " is Synthetic:" + field.isSynthetic());
        }

        /*
        结果:
        name is Synthetic:false
        age is Synthetic:false
        cat is Synthetic:false
        child is Synthetic:false
        =================================
        this$0 is Synthetic:true    Synthetic(如果是源码中未出现的属性、方法、或者类 这个方法返回的就是true)
         */
    }
}

@Data
class Person {

    private String name;

    private Integer age;

    Cat cat;

    Child child = new Child();

    class Child {

    }
}

@Data
class Cat {

    private String name;

    private int age;
}