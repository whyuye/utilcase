package com.wuhui.jvm;

public class IncrementTest {

    /**
     * 将一个局部变量加载到操作数栈的指令包括：iload、iload_、lload…
     * 将一个数值从操作数栈存储到局部变量表的指令包括：istore、istore_、lstore…
     *
     * @param args
     */
//    public static void main(String[] args) {
//        int j = 0;// 将常量0压入操作数栈，将常量0出栈赋值给局部变量j
//
//        j = j++;
//        // 1.将局部变量j压入操作数栈
//        // 2.对局部变量表中的j进行++
//        // 3.将操作数栈出栈赋值给局部变量j
//        System.out.println(j);
//    }

    /**
     * 将一个局部变量加载到操作数栈的指令包括：iload、iload_、lload…
     * 将一个数值从操作数栈存储到局部变量表的指令包括：istore、istore_、lstore…
     *
     * @param args
     */
    public static void main(String[] args) {
        int j = 0;// 将常量0压入操作数栈，将常量0出栈赋值给局部变量j

        j = ++j;
        // 1.将局部变量j进行++
        // 2.将局部变量j压入操作数栈中
        // 3.将操作数栈出栈赋值给局部变量j
        System.out.println(j);
    }
}
