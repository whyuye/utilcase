package com.wuhui.collectionframework;

import java.util.ArrayList;
import java.util.List;

public class ListTest3 {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();

        list1.addAll(new ArrayList<>(1));

        System.out.println(list1.size());
    }
}
