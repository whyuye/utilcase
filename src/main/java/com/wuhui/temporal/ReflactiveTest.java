package com.wuhui.temporal;

import lombok.Data;

import java.lang.reflect.Field;

public class ReflactiveTest {

    public static void main(String[] args) throws Exception {

        Person person = new Person();
        person.setName("testname");

        Class<?> personClass = person.getClass();
        Field[] fields = personClass.getDeclaredFields();
        for (Field field : fields) {
            Object propertyData = getPropertyData(person, field.getName());
            String testName = new String("testname");

            System.out.println(propertyData.equals(testName));
        }

    }

    public static Object getPropertyData(Object afterObj, String propertyName) throws NoSuchFieldException, IllegalAccessException {
        if (afterObj == null) {
            return null;
        }
        Field fieldForm = afterObj.getClass().getDeclaredField(propertyName);
        fieldForm.setAccessible(true);
        return fieldForm.get(afterObj);
    }
}

@Data
class Person {

    String name;
}