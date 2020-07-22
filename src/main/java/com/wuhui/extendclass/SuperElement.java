package com.wuhui.extendclass;

public class SuperElement {

    private SuperElement() {

    }
}
//
// SuperElement显示的提供了一个私有的构造方法，不可被继承。因为继承的子类中的构造方法会隐式的调用父类的构造方法
//class DocElement extends SuperElement {
//
//}