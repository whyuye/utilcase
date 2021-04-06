package com.wuhui.reflect;

import lombok.Value;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReflectTest6 {

    public static void main(String[] args) {
        List<TestObject> testObjects = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            testObjects.add(TestObject.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        }

        final Field[] declaredFields = TestObject.class.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
        }

        LocalDateTime start = LocalDateTime.now();
        for (TestObject testObject : testObjects) {
            for (Field field : declaredFields) {
                try {
                    field.get(testObject);
                } catch (IllegalAccessException e) {
                    System.out.println("出现异常");
                }
            }
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException exception) {
            System.out.println("被打断");
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println(Duration.between(start, end).toMillis());

    }

}

@Value(staticConstructor = "of")
class TestObject {

    private Integer field1;

    private Integer field2;

    private Integer field3;

    private Integer field4;

    private Integer field5;

    private Integer field6;

    private Integer field7;

    private Integer field8;

    private Integer field9;

    private Integer field10;
}
