package com.wuhui.universal;

import lombok.Data;
import lombok.ToString;

public class OverrideTest {

    public static void main(String[] args) {

        Super object = new Super();
        Super extendSuper = new ExtendSuper();

        object.superMethod();
        System.out.println("=============");
        extendSuper.superMethod();
    }
}

@Data
@ToString
class Super {

    protected void superHelloWorld() {
        System.out.println("Super hello world");
    }

    protected void superMethod() {
        System.out.println(this);
        superHelloWorld();
    }
}

@Data
@ToString
class ExtendSuper extends Super {

    @Override
    public void superHelloWorld() {
        System.out.println("ExtendSuper hello world");
    }

    @Override
    public void superMethod() {
        super.superMethod();
    }
}