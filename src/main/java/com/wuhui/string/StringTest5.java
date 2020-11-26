package com.wuhui.string;

public class StringTest5 {

    public static void main(String[] args) {

        //String str = new String("hello");
        //String str = new String(new char[]{'h', 'e', 'l', 'l', 'o'});
        String str1 = "hel";
        final String str = str1 + "lo"; // 变量和字符串，可以从生成的字节码看出是通过StringBuilder创建新的String
        String str2 = "hello";
        //String str = new String("hello");
        //String str = new String(new char[]{'h', 'e', 'l', 'l', 'o'});


        System.out.println(str == str2);

    }

    private static String getStr() {
        String str1 = "hel";
        return str1 + "lo";
    }
}
