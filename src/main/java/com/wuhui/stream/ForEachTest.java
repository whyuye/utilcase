package com.wuhui.stream;

public class ForEachTest {

    public static void main(String[] args) {

        go:
        for (int i = 0; i <= 10; i++) {

            System.out.println(i);
            if (i == 5) {
                break go;
            }
        }
    }
}
