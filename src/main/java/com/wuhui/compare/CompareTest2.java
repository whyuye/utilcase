package com.wuhui.compare;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CompareTest2 {

    public static void main(String[] args) {
        Person p1 = new Person(9, "aa");
        p1.setCars(new ArrayList<>());
        Car car10 = new Car();
        car10.setCarName("car10");
        Car car11 = new Car();
        car11.setCarName("car11");
        p1.getCars().add(car10);
        p1.getCars().add(car11);

        Person p2 = new Person(3, "aa");
        p2.setCars(new ArrayList<>());
        Car car20 = new Car();
        car20.setCarName("car20");
        Car car21 = new Car();
        car21.setCarName("car21");
        Car car23 = new Car();
        car23.setCarName("car23");
        p2.getCars().add(car20);
        p2.getCars().add(car21);
        p2.getCars().add(car23);

        List<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        Function<Person, Integer> function = p -> -p.getCars().size();
        personList = personList.stream().sorted(Comparator.comparing(function).thenComparing(Person::getId)).collect(Collectors.toList());
        System.out.println(personList);

        Consumer<Person> consumer = (Person p) -> System.out.println(p.toString());
        personList.stream().sorted(Comparator.comparing((Person p) -> -p.getCars().size()).thenComparing(Person::getId)).collect(Collectors.toList());

        Generic<Object> generic = new Generic<>();
        generic.helloGeneric(personList);
    }
}

@Data
class Car {

    private String carName;
}

@Data
class Person {

    private int id;

    private String name;

    private List<Car> cars;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Generic<T> {

    void helloGeneric(T a) {
        System.out.println(a);
    }
}