package com.wuhui.sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompareTest {

    public static void main(String[] args) {
        Person p1 = new Person("1", 1);
        Person p2 = new Person("4", 2);
        Person p3 = new Person("2", 2);
        Person p4 = new Person("3", 2);
        Person p5 = new Person("5", 3);
        Person p6 = new Person("7", 4);
        Person p7 = new Person("6", 4);
        Person p8 = new Person("8", 5);
        Person p9 = new Person("9", 6);
        Person p10 = new Person("10", 6);
        Person p11 = new Person("11", 7);

        List<Person> personList = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11);

        Set<Person> sortedPersonList = personList.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toSet());

        for (Person person : sortedPersonList) {
            System.out.println(person);
            System.out.println("===============");
        }

        System.out.println("========================无序");
        Set<Person> personSet = new HashSet<>(personList);

        List<Person> sortedPersonSet = personSet.stream()
                .sorted(Comparator.comparing(Person::getAge)) // sort是否稳定，跟生成的流是否是顺序流有关系，如果是顺序流的话就是稳定的
                .collect(Collectors.toList());

        for (Person person : sortedPersonSet) {
            System.out.println(person);
            System.out.println("===============");
        }
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {

    private String name;

    private Integer age;
}