package com.wuhui.string;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringFormatTest {

    public static void main(String[] args) {

        String format = String.format("%08d", 1000); // 10表示多少位，不足就自动补0
        System.out.println(format);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String dateTimeStr = fmt.format(LocalDateTime.now());

        System.out.println(dateTimeStr);

        final Long aLong = Long.valueOf(dateTimeStr);
        System.out.println(aLong);

        final double v = Math.log10(100);
        System.out.println(v);
    }
}
