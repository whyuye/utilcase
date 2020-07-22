package com.wuhui.collectionframework;

import java.util.ArrayList;
import java.util.List;

public class CollectionTest {

    public static void main(String[] args) {

        List<String> list1 = new ArrayList();
        List<String> list2 = new ArrayList();

        list1.add("1");
        list1.add("2");
        list1.add("2");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        list1.add("6");


        list2.add("2");
        list2.add("2");
        list2.add("5");
        list2.add("6");

        list1.remove("2");
        list1.remove("2");

        System.out.println(list1);
    }
}
