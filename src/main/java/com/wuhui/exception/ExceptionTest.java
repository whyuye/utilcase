package com.wuhui.exception;

public class ExceptionTest {

    /**
     * Exception in thread "main" java.lang.RuntimeException: runtime exception
     * 	at com.wuhui.exception.ExceptionTest.method2(ExceptionTest.java:14) // method2最后压栈，最先弹栈
     * 	at com.wuhui.exception.ExceptionTest.method1(ExceptionTest.java:10) // method1第二个压栈
     * 	at com.wuhui.exception.ExceptionTest.main(ExceptionTest.java:6) // main先压栈，最后弹栈
     *
     * @param args
     */
    public static void main(String[] args) {
        method1();
    }

    private static void method1() {
        method2();
    }

    private static void method2() {
        throw new RuntimeException("runtime exception");
    }
}
