package com.wuhui.string;

public class StringTest {

    public static void main(String[] args) {
        testBooleanParam(null);
        testBooleanParam(false);
        testBooleanParam(true);
    }

    private static void testBooleanParam(Boolean boolParam) {
        System.out.println("param is " + boolParam);
    }
}
