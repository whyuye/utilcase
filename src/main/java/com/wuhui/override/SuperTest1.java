package com.wuhui.override;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SuperTest1 {

    public static void main(String[] args) {

        SuperClass superClass = new ExtendClass(new SimpleData("hello")); // 并不会覆盖父类的成员变量
        superClass.useData();

    }
}

@Setter
abstract class SuperClass {

    protected SimpleData simpleData;

    void useData() {
        System.out.println(simpleData.getName());;
    }
}

class ExtendClass extends SuperClass {

    protected SimpleData simpleData;

    public ExtendClass(SimpleData simpleData) {
        super.setSimpleData(simpleData);
        this.simpleData = simpleData;
    }
}

@NoArgsConstructor
@AllArgsConstructor
class SimpleData {
    private String name;

    public String getName() {
        return this.name;
    }
}