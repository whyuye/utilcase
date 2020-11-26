package com.wuhui.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectTest2 {

    /**
     * 校验使用反射带参数的构造函数创建对象的时候传入null值会不会出现异常
     *
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        // 下面三个都可以获取到全类名 com.wuhui.reflect.BeReflectedObject
        System.out.println(BeReflectedObject.class.getTypeName());
        System.out.println(BeReflectedObject.class.getCanonicalName());
        System.out.println(BeReflectedObject.class.getName());
        // 获取简单的类型 BeReflectedObject
        System.out.println(BeReflectedObject.class.getSimpleName());

        Class clazz = Class.forName(BeReflectedObject.class.getName()); // throw ClassNotFoundException
        Constructor constructor = clazz.getConstructor(Integer.class, String.class, int.class); // throw NoSuchMethodException

        try {
            // 由于构造方法的参数是基本类型，导致利用反射的时候，通过null去赋值给int的时候出现IllegalArgumentException异常
            BeReflectedObject beReflectedObject = (BeReflectedObject)constructor.newInstance(1, "null", null);
            System.out.println(beReflectedObject);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

class BeReflectedObject {

    private Integer objectNum1 = 1;
    private String objectName = "reflect object";
    private Integer objectNum2 = 2;

    public BeReflectedObject(Integer objectNum1, String objectName, /*构造方法参数尽量不要用基本类型*/int objectNum2) {
        this.objectNum1 = objectNum1;
        this.objectName = objectName;
        this.objectNum2 = objectNum2;
    }

    @Override
    public String toString() {
        return "BeReflectedObject{" +
                "objectNum1=" + objectNum1 +
                ", objectName='" + objectName + '\'' +
                ", objectNum2=" + objectNum2 +
                '}';
    }
}
