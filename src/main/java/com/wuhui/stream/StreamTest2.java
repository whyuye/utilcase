package com.wuhui.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest2 {

    public static void main(String[] args) {

//        Integer[] nums2 = {1, 2, 3};
//        Integer[] nums3 = {3, 4};
//        List<Integer> nums2List = Arrays.asList(nums2);
//        List<Integer> nums3List = Arrays.asList(nums3);
//
//        //使用2个map嵌套过滤
//        List<int[]> res2 = nums2List.stream().flatMap(i -> nums3List.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
//        System.out.println(res2.size());
//
//        System.out.println("--------------------------------");
//        //Demo3:针对Demo2和Demo1组合返回总和能被3整除的数对
//        //(2,4)和(3,3)是满足条件的
//        List<int[]> res3 = nums2List.stream().flatMap(i -> nums3List.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(Collectors.toList());
//        System.out.println(res3.size());

        List<List<Integer>> list = new ArrayList<>();

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list1.add(1);
        list1.add(3);
        list1.add(5);
        list2.add(2);
        list2.add(4);
        list2.add(6);

        list.add(list1);
        list.add(list2);

        String str = String.format("%d + %s", 1, "name");
        System.out.printf("%d + %s\n", 1, "name");
        // 扁平化处理。将多维列表转化为单维列表
        list.stream().flatMap(Collection::stream)
                .sorted()
                .forEach(i -> System.out.println(Thread.currentThread().getName() + i));
    }
}
