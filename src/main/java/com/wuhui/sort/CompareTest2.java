package com.wuhui.sort;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CompareTest2 {

    public static void main(String[] args) {
        List<MyCompareClass> noSortedMyCompareClasses = Lists.newArrayList(new MyCompareClass(6), new MyCompareClass(2), new MyCompareClass(1), new MyCompareClass(3), new MyCompareClass(4));

        List<MyCompareClass> sortedMyCompareClasses = noSortedMyCompareClasses.stream().sorted(Comparator.comparing(MyCompareClass::getNum)).collect(Collectors.toList());

        System.out.println(sortedMyCompareClasses);

        String sortedMyCompareClassesStr = JSON.toJSONString(sortedMyCompareClasses);
        System.out.println(sortedMyCompareClassesStr);

        List<MyCompareClass> myCompareClasses = JSON.parseArray(sortedMyCompareClassesStr, MyCompareClass.class);
        System.out.println(myCompareClasses);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class MyCompareClass {

    private Integer num;
}
