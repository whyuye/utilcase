package com.wuhui.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class TimeTest3 {

    public static void main(String[] args) {
        long endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).minus(30, ChronoUnit.DAYS).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(endTime);
    }
}
