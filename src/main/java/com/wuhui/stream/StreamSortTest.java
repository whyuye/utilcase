package com.wuhui.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamSortTest {

    public static void main(String[] args) {
        Test test1 = new Test("a", 1, 1);
        Test test2 = new Test("b", 2, 2);
        Test test3 = new Test("c", 3, 3);
        Test test4 = new Test("d", 4, 2);
        Test test5 = new Test("e", 5, 4);
        Test test6 = new Test("f", 6, 5);
        Test test7 = new Test("g", 1, 3);
        Test test8 = new Test("h", 1, 2);
        Test test9 = new Test("j", 1, 1);
        Test test10 = new Test("k", 5, 1);
        Test test11 = new Test("a", 5, 4);
        Test test12 = new Test("b", 5, 2);

        List<Test> lists =
                Arrays.asList(test1, test2, test3, test4, test5, test6, test7, test8, test9, test10, test11, test12);
        // 分组
        Map<String, List<Test>> maps = lists.parallelStream().collect(Collectors.groupingBy(Test::getName));

        System.out.println(maps.get("a"));
        System.out.println(maps.get("b"));
        System.out.println(maps.get("c"));
        // 根据年龄排序
//        List<Test> newList = lists.stream().sorted(Comparator.comparingInt(Test::getAge)).collect(Collectors.toList());
//        newList.forEach(test -> System.out.println(test + System.lineSeparator()));
//        System.out.println("================");
//        List<Test> collect = newList.stream().sorted(Comparator.comparing(Test::getNum, Comparator.reverseOrder())).collect(Collectors.toList());
//        collect.forEach(test -> System.out.println(test + "\n"));
    }
}

class Test {

    private String name;
    private Integer age;
    private Integer num;

    public Test(String name, Integer age, Integer num) {
        this.name = name;
        this.age = age;
        this.num = num;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", num=" + num +
                '}';
    }
}