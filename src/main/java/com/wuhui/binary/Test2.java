//package com.wuhui.binary;
//
//import java.util.Stack;
//
//public class Test2 {
//
//    public static void main(String[] args) {
//
//    }
//}
//
//
//class MyQueue {
//
//    Object[] stack1;
//    Object[] stack2;// 倒序的
//
//    private int stack1CurLength;
//    private int stack2CurLength;
//
//    public MyQueue(int length) {
//        // 有界队列
//        stack1 = new Object[length];
//        stack2 = new Object[length];
//        stack1CurLength = 0;
//        stack2CurLength = length;
//    }
//
//    public boolean add(Object data) {
//        stack1[stack1CurLength] = data;
//        stack2[stack1CurLength - 1] = data;
//
//        stack1CurLength++;
//        stack1CurLength--;
//
//        return true;
//    }
//
//    /**
//     * 正向队列
//     * @return
//     */
//    public Object pollStack1() {
//        stack1CurLength--;
//        return stack1[0];
//    }
//
//    /**
//     * 反向队列
//     * @return
//     */
//    public Object pollStack2() {
//        stack2CurLength--;
//        return stack2[stack2CurLength - 1];
//    }
//
//    /**
//     * 和poll相比实现就是不会删数据
//     */
//    public Object peekStack1() {
//
//    }
//
//    /**
//     * 和poll相比实现就是不会删数据
//     */
//    public Object peekStack2() {
//
//    }
//
//}
