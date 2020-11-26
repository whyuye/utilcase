package com.wuhui.universal;

import java.util.regex.Pattern;

public class ProductUtils {

    public static void main(String[] args) {
        String s1 = ProductStrUtils.removeSpaceFromStr1("1      1");
        System.out.println(s1);

        String s2 = ProductStrUtils.removeSpaceFromStr2("1      1");
        System.out.println(s2);
    }
}


class ProductStrUtils {

    private static final Pattern BLANK_PATTERN = Pattern.compile(" ");

    /**
     * 去掉字符串中的空格
     * 这个方法的性能比下面一个方法的性能高，因为下面一个方法底层也是调用正则去处理，用str的话反而增加了一次寻址
     *
     * @param str
     * @return
     */
    public static String removeSpaceFromStr1(String str) {
        if (str == null) {
            return "";
        }
        return BLANK_PATTERN.matcher(str).replaceAll("");
    }

    public static String removeSpaceFromStr2(String str) {
        if (str == null || " ".equals(str)) {
            return "";
        }
        return str.replaceAll(" ", "");
    }
}
