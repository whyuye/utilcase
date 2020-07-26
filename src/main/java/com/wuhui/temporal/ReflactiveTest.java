package com.wuhui.temporal;

import lombok.Data;

import java.lang.reflect.Field;

public class ReflactiveTest {

    public static void main(String[] args) throws Exception {

        Person newPerSon = new Person();
        newPerSon.setAge(0);
        Person oldPerson = new Person();
        oldPerson.setName("testname");
        oldPerson.setAge(0);

        Class<?> personClass = newPerSon.getClass();
        Field[] fields = personClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            Object afterValue = getPropertyData(newPerSon, field.getName());
            if (afterValue == null) {
                continue;
            }

            Object beforeValue = getBeforePropertyDataStr(oldPerson, field.getName());


            System.out.println(afterValue.equals(beforeValue));
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

    private static String getBeforePropertyDataStr(Object afterObj, String propertyName) throws NoSuchFieldException, IllegalAccessException {
        if (afterObj == null) {
            return "";
        }
        Field fieldForm = afterObj.getClass().getDeclaredField(propertyName);
        fieldForm.setAccessible(true);
        Object resultObj = fieldForm.get(afterObj);
        if (resultObj == null) {
            return "";
        }
        return resultObj.toString();
    }
}

@Data
class Person {

    String name;

    Integer age;
}