package com.wuhui.compare;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompareTest5 {

    public static void main(String[] args) {
        Map<Integer, String> noSortedMap = new HashMap<>();

        noSortedMap.put(5, "hello");
        noSortedMap.put(17, "hello");
        noSortedMap.put(6, "hello");
        noSortedMap.put(22, "hello");
        noSortedMap.put(8, "hello");
        noSortedMap.put(16, "hello");

        System.out.println(noSortedMap);

        System.out.println("=================");

        List<Integer> keyList = noSortedMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // 有状态
                .map(Map.Entry::getKey) // 无状态
                .collect(Collectors.toList()); // 终结方法

        List<String> valueList = noSortedMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // 有状态
                .map(Map.Entry::getValue) // 无状态
                .collect(Collectors.toList()); // 终结方法

        System.out.println(keyList);
        System.out.println(valueList);
    }
}
