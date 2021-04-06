package com.wuhui.binary;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class BitCalculator {

    public static void main(String[] args) {

        System.out.println(6 ^ 6); // 按位异或

        String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                .format(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()),
                        ZoneId.systemDefault()));

        System.out.println(time.substring(2));

        System.out.println(1.0 / 1);
        System.out.println(1.0 / 0);
    }
}
