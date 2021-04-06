package com.wuhui.string;

public class StringTest7 {

    public static void main(String[] args) {
        String str1 = "abc";
        StringWrapperFirst stringWrapperFirst = new StringWrapperFirst();

        System.out.println(stringWrapperFirst.getInnerString() == str1); // true
        System.out.println(stringWrapperFirst.getInnerString().intern() == str1); // true

        System.out.println("=================");

        StringWrapperSecond stringWrapperSecond = new StringWrapperSecond("abc");
        System.out.println(stringWrapperSecond.getInnerString() == str1); // true
        System.out.println(stringWrapperSecond.getInnerString().intern() == str1); // true
    }

}

class StringWrapperFirst {
    private String innerString = "abc";

    public String getInnerString() {
        return innerString;
    }
}

class StringWrapperSecond {
    private String innerString;

    public StringWrapperSecond(String innerString) {
        this.innerString = innerString;
    }

    public String getInnerString() {
        return innerString;
    }
}
