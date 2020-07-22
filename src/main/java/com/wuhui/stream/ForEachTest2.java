package com.wuhui.stream;

public class ForEachTest2 {

    public static void main(String[] args) {

        int size = 400;
        int dubbo_max_size = 500;

        int maxPageSize = size / dubbo_max_size == 0
                ? (size / dubbo_max_size)
                : (size / dubbo_max_size + 1);

        for (int nowPage = 0; nowPage < maxPageSize; nowPage++) {
            System.out.println(1);
        }
    }
}
