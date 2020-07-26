package com.wuhui.aqs;

import java.util.ArrayList;
import java.util.List;

public class Concurrent {

    public static void main(String[] args) throws InterruptedException {

        List<Integer> list = new ArrayList();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        Thread a = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.remove(i);
            }
        }, "a");

        Thread b = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        }, "b");

        a.start();
        b.start();

        a.join();
        b.join();
    }
}
