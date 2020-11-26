package com.wuhui.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest4 {

    private static Pattern pattern = Pattern.compile("\\d+");

    public static void main(String[] args) {
        String str1 = "10盒/箱";
        String str2 = "48";
        String str3 = "1件";
        String str4 = "6瓶/箱";
        String str5 = "12";
        String str6 = "1";
        String str7 = "24";
        String str8 = "10盒/箱520";
        String str9 = "0";
        String str10 = "hello world";
        String str11 = "真不错";
        String str12 = "&！@342312";

        String[] stringTest5s = {str1, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12};

        for (String str : stringTest5s) {
            Integer intBoxRegulation = sliceBoxRegulationIntegerPrefix(str);
            System.out.println(str + " slice result: " + intBoxRegulation);
        }

    }

    private static Integer sliceBoxRegulationIntegerPrefix(String boxRegulation) {
        if (boxRegulation == null || "".equals(boxRegulation.trim())) {
            return 0;
        }

        // 判断字符串是不是以数字开头
        Matcher firstCharMatcher = pattern.matcher(boxRegulation.charAt(0) + "");
        if (!firstCharMatcher.matches()) {
            return 0;
        }

        // 以字符串开头，截取所有整数前缀做为返回值
        Matcher strMatcher = pattern.matcher(boxRegulation);
        if (strMatcher.find()) {
            return Integer.parseInt(strMatcher.group());
        }

        return 0;
    }
}
