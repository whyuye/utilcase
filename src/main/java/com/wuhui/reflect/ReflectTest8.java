package com.wuhui.reflect;

public class ReflectTest8 {

    public static void main(String[] args) {
        final String implementationVersion1 = interfaceTest.class.getPackage().getImplementationVersion();
        System.out.println(implementationVersion1);
        final String specificationVersion1 = interfaceTest.class.getPackage().getSpecificationVersion();
        System.out.println(specificationVersion1);

        final String implementationVersion2 = interfaceImplTest.class.getPackage().getImplementationVersion();
        System.out.println(implementationVersion2);
        final String specificationVersion2 = interfaceImplTest.class.getPackage().getSpecificationVersion();
        System.out.println(specificationVersion2);

        final String packageName = interfaceImplTest.class.getPackage().getName();
        System.out.println(packageName);
    }
}

interface interfaceTest {

}

class interfaceImplTest implements interfaceTest {

}
