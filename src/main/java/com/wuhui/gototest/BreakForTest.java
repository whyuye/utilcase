package com.wuhui.gototest;

public class BreakForTest {
    static void retryContinue() {
        System.out.println("执行retryContinue:");
        int i = 0, j = 0;
        retry:
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 5; j++) {
                System.out.println(j);
                if (j == 3) {
                    continue retry;
                }
            }
        }
        System.out.printf("after loop, i = %d, j=%d", i, j);
    }

    static void retryBreak() {
        System.out.println("执行retryBreak:");
        int i = 0, j = 0;
        retry1:
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 5; j++) {
                System.out.println(j);
                if (j == 3) break retry1;
            }

        }
        System.out.printf("after loop, i = %d, j=%d\n", i, j);
    }

    static void whileLabel() {
        System.out.println("执行whileLabel:");
        int i = 0;
        int j = 0;
        whileLabel:
        while (i++ < 10) {
            while (j < 10) {
                System.out.println(j);
                if (j == 6) break whileLabel;
                ++j;
            }
        }
        System.out.printf("after loop, i = %d, j=%d\n", i, j);
    }

    public static void main(String[] args) {
        retryContinue();
        System.out.println("***********************");
        retryBreak();
        System.out.println("***********************");
        whileLabel();
    }
}
