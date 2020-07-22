package com.wuhui.override;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.ThreadFactory;

public class SuperClass {

    public void hello(){

        System.out.println(getClass());
    }

    public static void main(String[] args) {
        SuperClass superClass = new SuperClass();
        superClass.hello();
        SuperClass extendSuperClass = new ExtendSuperClass();
        extendSuperClass.hello();
        SuperClass extendExtendSuperClass = new ExtendExtendSuperClass();
        extendExtendSuperClass.hello();

        System.out.println(superClass.newDefaultThreadFactory());
    }

    protected ThreadFactory newDefaultThreadFactory() {
        System.out.println(getClass());
        return new DefaultThreadFactory(getClass());
    }
}

class ExtendSuperClass extends SuperClass {

    @Override
    public void hello() {
        super.hello();
    }
}

class ExtendExtendSuperClass extends ExtendSuperClass {

    @Override
    public void hello() {
        super.hello();
    }
}
