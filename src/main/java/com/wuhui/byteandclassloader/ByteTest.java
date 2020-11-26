package com.wuhui.byteandclassloader;

public class ByteTest {

    public static void main(String[] args) {
        // 0: new           #2                  // class com/wuhui/byteandclassloader/ByteTest
        //         3: dup
        //         4: invokespecial #3                  // Method "<init>":()V
        //         7: astore_1 （将栈中的数据放到局部变量中）
        ByteTest b1 = new ByteTest();
        ByteTest b2 = new ByteTest();

        b1 = b2;
        // 这一步返回false的原因就是，生成字节码指令的时候，开始比较后，就已经把局部变量中的b1,b2压入到操作数栈中了
        System.out.println(b1 == (b1 = b2)); // 基于栈的指令集，jvm任何操作都是操作数栈的操作
        // (b1 = b2） 这一步操作是没有返回值的，所以需要在操作数栈中拷贝一份b2
        // 19-20 aload_1,aload_2指令
        // 此时操作栈->
        // b2
        // b1
        // 21 dup指令
        // 此时操作栈->
        // b2
        // b2
        // b1
        //isEquals(b1, b2);
    }

    private static void isEquals(ByteTest b1, ByteTest b2) {
        System.out.println(b1 == (b1 = b2));
    }
}
