package com.wuhui.divide;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class IntTest {

    public static void main(String[] args) {

//        int a = 1;
//        int b = 3;
//
//        long c = Math.round((double)2 / 3 * 10000);
//
//        System.out.println((int)c);

        BigDecimal bigDecimal = new BigDecimal(String.valueOf((double)2 / 3 * 10000));

        System.out.println(bigDecimal.doubleValue());
    }
}
