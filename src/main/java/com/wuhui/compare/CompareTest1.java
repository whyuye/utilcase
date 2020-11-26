package com.wuhui.compare;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CompareTest1 {

    public static void main(String[] args) {

        BigDecimal a = new BigDecimal("2");
        BigDecimal o = new BigDecimal("3");
        // 0.6666 -> 0.666  向下了
        System.out.println(a.divide(o, 3, RoundingMode.FLOOR).doubleValue());

        BigDecimal lingdianyi = new BigDecimal(0.10);
        System.out.println(lingdianyi);
        System.out.println(lingdianyi.doubleValue());

        BigDecimal linddianyi2 = new BigDecimal(0.1);
        System.out.println(linddianyi2);
        System.out.println(lingdianyi.doubleValue());
        System.out.println(lingdianyi.equals(linddianyi2));

        System.out.println(0.2 + 0.1);

        BigDecimal doubleStr = new BigDecimal(1.111111111);
        System.out.println(doubleStr);

        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0.1);
        System.out.println(bigDecimalValueOf.equals(linddianyi2));
        System.out.println(bigDecimalValueOf.scale());
        System.out.println(linddianyi2.scale());
    }
}
