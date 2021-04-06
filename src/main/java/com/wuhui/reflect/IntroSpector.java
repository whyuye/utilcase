package com.wuhui.reflect;

import lombok.Getter;
import lombok.Setter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class IntroSpector {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(IntroSpectorBean.class, Object.class);// 不自省从父类继承的属性
        PropertyDescriptor[] pds = info.getPropertyDescriptors();// 取得属性描述器
        for (PropertyDescriptor pd : pds) {
            System.out.println(pd.getName());
            System.out.println(pd.getPropertyType());
            System.out.println(pd.getPropertyType() == Integer.TYPE);
            System.out.println(pd.getWriteMethod() == null ? null : pd.getWriteMethod().getName());
            System.out.println(pd.getReadMethod() == null ? null : pd.getReadMethod().getName());
            System.out.println("-------------");
        }

        System.out.println("===============SEPARATOR================");
        BeanInfo info2 = Introspector.getBeanInfo(IntroSpectorBeanChild.class, Object.class, Introspector.IGNORE_ALL_BEANINFO);
        PropertyDescriptor[] pds2 = info2.getPropertyDescriptors();// 取得属性描述器
        for (PropertyDescriptor pd : pds2) {
            System.out.println(pd.getName());
            System.out.println(pd.getPropertyType());
            System.out.println(pd.getPropertyType() == String.class);
            System.out.println(pd.getWriteMethod() == null ? null : pd.getWriteMethod().getName());
            System.out.println(pd.getReadMethod() == null ? null : pd.getReadMethod().getName());
            System.out.println("-------------");
        }
    }
}

@Setter
@Getter
class IntroSpectorBean {

    int basicTypeNum;

    Integer primitiveTypeNum;

    String beanName;
}

@Setter
@Getter
class IntroSpectorBeanBase {

    int basicTypeNum;

    Integer primitiveTypeNum;

    String beanName;
}

@Setter
@Getter
class IntroSpectorBeanChild extends IntroSpectorBeanBase {

    String childName;
}

