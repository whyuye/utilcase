package com.wuhui.pattern;

import java.util.regex.Pattern;

public class PatternTest2 {

    private static final Pattern PATTERN = Pattern.compile("ab{1,3}+c");

    public static void main(String[] args) {
        String test = "abbc";
        System.out.println(PATTERN.matcher("abbc").matches());
    }
}
