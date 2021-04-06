package com.wuhui.classcode;

import lombok.Getter;

public class FinalTest {

    public static void main(String[] args) {
        TestObject testObject1 = new TestObject(1);
        TestObject testObject2 = new TestObject(2);

        System.out.println(testObject1.getNumber());
        System.out.println(testObject2.getNumber());
        System.out.println(testObject1.getNumber());
    }
}

@Getter
class TestObject {

    private static int number;

    public TestObject(int i) {
        this.number = i;
    }

    public int getNumber() {
        return number;
    }
}
