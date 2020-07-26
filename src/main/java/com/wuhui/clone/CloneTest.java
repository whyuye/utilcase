package com.wuhui.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 输出结果：
 * [6, 2, 3, 4, 5, 301]
 * [1, 2, 3, 4, 5, 300]
 * =======================
 * [Element(num=2), Element(num=1), Element(num=3)]
 * [Element(num=2), Element(num=1), Element(num=3)]
 *
 * 从结果可以很清晰的clone方法是浅拷贝
 *
 */
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        Integer[] intList = Arrays.asList(1, 2, 3, 4, 5, 300).toArray(new Integer[0]);

        Integer[] intListClone = intList.clone();

        intList[0] = 6;
        intList[5] = 301;
        System.out.println(Arrays.toString(intList));
        System.out.println(Arrays.toString(intListClone));

        System.out.println("=======================");

        Element element1 = new Element();
        element1.setNum(1);
        Element element2 = new Element();
        element2.setNum(2);
        Element element3 = new Element();
        element3.setNum(3);

        Element[] eleList = Arrays.asList(element1, element2, element3).toArray(new Element[0]);

        Element[] eleListClone = eleList.clone();

        eleList[0].setNum(2);
        eleListClone[1].setNum(1);
        System.out.println(Arrays.toString(eleList));
        System.out.println(Arrays.toString(eleListClone));

        CloneObject cloneObject = new CloneObject();
        System.out.println(cloneObject);
        System.out.println(cloneObject.clone());
        System.out.println(cloneObject == cloneObject.clone());

        Element[] elements = {new Element(1), null, new Element(2), new Element()};
        Stream.of(elements).filter(Objects::nonNull).forEach(System.out::println);
        System.out.println(elements.length);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Element {

    private int num;

}

@Data
@ToString
class CloneObject {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //Object clone = super.clone();
        // super.clone(); 如果调用了Object的clone方法，那么一定要实现Cloneable接口。
        return this;
    }
}