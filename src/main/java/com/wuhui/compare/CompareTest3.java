package com.wuhui.compare;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class CompareTest3 {

    public static void main(String[] args) {
        List<Integer> intList = Lists.newArrayList();
        intList.add(1);
        intList.add(5);
        intList.add(3);
        intList.add(2);
        intList.add(4);
        intList.add(6);

        boolean isDesc = true; // 是否倒序
        List<Integer> sortedIntList = intList.stream().sorted((c1, c2) -> isDesc ? c2.compareTo(c1) : c1.compareTo(c2)).collect(Collectors.toList());

        System.out.println(sortedIntList);
    }
}
