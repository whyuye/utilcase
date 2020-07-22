package com.wuhui.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
@Builder
//@NoArgsConstructor
//@NoArgsConstructor
//@AllArgsConstructor
public class LombokClass {

    private String name;

    private Integer age;

    //private final String className;

    public static void main(String[] args) {

        LombokClass lombokClass = new LombokClass("1", 1);
//        System.out.println(lombokClass);
    }
}
