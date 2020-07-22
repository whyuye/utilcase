package com.wuhui.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringTest3 {

    public static void main(String[] args) {

        Object src = new String("123");
        String dest = "123";

        System.out.println(isEqualsStr(src, dest));

        List<String> list = new ArrayList<>();

        list.add("321");
        list.add("123");

        String[] strings = list.toArray(new String[0]);
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));
    }

    private static boolean isEqualsStr(Object src, String dest) {
        return src.equals(dest);
    }
}
