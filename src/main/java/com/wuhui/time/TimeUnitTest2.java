package com.wuhui.time;

import java.util.concurrent.TimeUnit;

public class TimeUnitTest2 {

    public static void main(String[] args) {
        // 36个月
        int value1 = 36;
        long monthTimeStamp = TimeUnit.DAYS.toMillis(value1 * 30L);
        System.out.println(monthTimeStamp);

        // 5天
        int value2 = 5;
        long dayTimeStamp1 = TimeUnit.DAYS.toMillis(value2);
        System.out.println(dayTimeStamp1);

        // 2天
        int value3 = 2;
        long dayTimeStamp2 = TimeUnit.DAYS.toMillis(value3);
        System.out.println(dayTimeStamp2);

        System.out.println("================");

        // compare
        long timeExpiredDanger = 1521820800000L + 93312000000L - 432000000L;
        long maxTimeExpiredDanger = 1615978186909L + 172800000L;

        System.out.println(timeExpiredDanger);
        System.out.println(maxTimeExpiredDanger);

        long timeExpiredDanger1 = 1615996800000L + 259200000L;
        System.out.println(timeExpiredDanger1);
    }
}
