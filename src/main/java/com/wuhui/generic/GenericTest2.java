package com.wuhui.generic;

public class GenericTest2 {

    public static void main(String[] args) {
        DefaultFactory defaultFactory = new DefaultFactory();

        System.out.println(defaultFactory.getBean("bean"));
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}

class DefaultFactory {

    public Object getBean(String name) {
        return doGetBean(name, null);
    }

    public <T> T doGetBean(String name, Class<T> requiredType) {
        Object bean;
        bean = createBean(name);

        System.out.println(requiredType);

        return (T) bean;
    }

    public Object createBean(String name) {
        return new Object();
    }
}
