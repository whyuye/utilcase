package com.wuhui.time;

import java.util.concurrent.TimeUnit;

public class TImeUnitTest {

    public static void main(String[] args) {
        Long nowTime = System.currentTimeMillis();
        System.out.println(TimeUnit.MINUTES.convert(nowTime - getDayStartTimestamp(nowTime), TimeUnit.MILLISECONDS));
        System.out.println(19 * 60 + 18);
    }

    public static long getDayStartTimestamp(long timestamp) {
        System.out.println(timestamp);
        System.out.println(getDayStartTimestamp(timestamp, 8));
        return getDayStartTimestamp(timestamp, 8);
    }

    public static long getDayStartTimestamp(long timestamp, int timezone) {
        System.out.println((timestamp + 3600000L * (long)timezone) % 86400000L);
        return timestamp - (timestamp + 3600000L * (long)timezone) % 86400000L;
    }
}
