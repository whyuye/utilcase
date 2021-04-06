package com.wuhui.pattern;

import java.util.regex.Pattern;

public class PatternTest {

    private static final Pattern UUID_PATTERN = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}");

    private static final Pattern NUMBERIC_PATTERN = Pattern.compile("^[-+]?[\\d]+$");
    private static final Pattern ZERO_PATTERN = Pattern.compile("^[0]+");

    public static void main(String[] args) {
        System.out.println("Pattern.matches===================");
        System.out.println(UUID_PATTERN.matcher("00000000-0000-0000-0000-000000000000").matches()); // false
        System.out.println(UUID_PATTERN.matcher("00000000-0000-1000-a000-000000000000").matches()); // true

        String numShow1 = "0000123";
        String numShow2 = "0000123000";
        String numShow3 = "1100123000";
        System.out.println(isNumeric(numShow1));
//
//        final String format = String.format("%d", numShow);
//        System.out.println(format);

        System.out.println("Matcher.replaceAll===================");
        final String s1 = ZERO_PATTERN.matcher(numShow1).replaceAll("");
        System.out.println(s1);
        final String s2 = ZERO_PATTERN.matcher(numShow2).replaceAll("");
        System.out.println(s2);
        final String s3 = ZERO_PATTERN.matcher(numShow3).replaceAll("");
        System.out.println(s3);
        System.out.println("Integer.parseInt===================");
        final int i1 = Integer.parseInt(numShow1);
        System.out.println(i1);
        final int i2 = Integer.parseInt(numShow2);
        System.out.println(i2);
        final int i3 = Integer.parseInt(numShow3);
        System.out.println(i3);
    }

    public static boolean isNumeric(String str) {
        return NUMBERIC_PATTERN.matcher(str).matches();
    }
}
