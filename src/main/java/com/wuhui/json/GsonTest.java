package com.wuhui.json;

import lombok.AllArgsConstructor;
import lombok.Data;

public class GsonTest {

    public static void main(String[] args) {
        String ruleId = "123";
        String stringJson = JsonUtils.toJson(ruleId);
        System.out.println(stringJson);

        Person person = new Person("123", 1);
        String personJson = JsonUtils.toJson(person);
        System.out.println(personJson);

        System.out.println(JsonUtils.fromJson(personJson, Person.class));

        setValueToMethod();
    }

    private static <V> boolean equalClass(Class<V> clazz) {
        System.out.println(String.class.equals(clazz)); // 如果clazz是String那就是true
        return clazz == String.class;
    }

    private static void setValueToMethod() {
        Class<String> clazz1= String.class;
        System.out.println(equalClass(clazz1)); // true

        Class<Person> clazz2 = Person.class;
        System.out.println(equalClass(clazz2)); // false
    }

}

@Data
@AllArgsConstructor
class Person {

    String name;

    Integer age;
}

