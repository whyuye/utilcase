package com.wuhui.collectionframework;

import java.util.ArrayList;
import java.util.List;

public class ListSubListTest {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> integers = list.subList(0, 2);
        System.out.println(integers);
    }
}
