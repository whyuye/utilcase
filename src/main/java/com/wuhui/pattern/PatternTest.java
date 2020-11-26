package com.wuhui.pattern;

import java.util.regex.Pattern;

public class PatternTest {

    private static final Pattern UUID_PATTERN = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}");

    public static void main(String[] args) {
        System.out.println(UUID_PATTERN.matcher("00000000-0000-0000-0000-000000000000").matches()); // false
        System.out.println(UUID_PATTERN.matcher("00000000-0000-1000-a000-000000000000").matches()); // true
    }
}
