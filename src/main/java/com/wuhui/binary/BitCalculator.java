package com.wuhui.binary;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class BitCalculator {

    public static void main(String[] args) {

        System.out.println(3 ^ 3);

        String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                .format(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()),
                        ZoneId.systemDefault()));

        System.out.println(time.substring(2));
    }
}
