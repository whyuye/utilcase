package com.wuhui.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class StringTest2 {

    public static void main(String[] args) {

        List<String> strLIst = new ArrayList<>();

        strLIst.add("1");
        strLIst.add("2");

        String result = joinwith(",", strLIst.toArray());
        System.out.println(result);
    }

    private static String joinwith(String join, Object... objects) {

        final StringBuilder result = new StringBuilder();

        final Iterator<Object> iterator = Arrays.asList(objects).iterator();
        while (iterator.hasNext()) {
            final String value = Objects.toString(iterator.next(), "");
            result.append(value);

            if (iterator.hasNext()) {
                result.append(join);
            }
        }

        return result.toString();

    }
}
