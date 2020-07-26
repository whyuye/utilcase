package com.wuhui.binary;

public class BitTest {

    public static void main(String[] args) {
        System.out.println(getNextAuditBitNum(10, 3));
        System.out.println(getNextAuditBitNum(14, 2));
        System.out.println(getNextAuditBitNum(14, 3));
        System.out.println(getNextAuditBitNum(14, 4));
        System.out.println(getNextAuditBitNum(1, 1));
        System.out.println(getNextAuditBitNum(3, 1));
    }

    private static int getNextAuditBitNum(Integer curAuditProcessBitMark, Integer prevBitNum) {
        int nextBitNum = prevBitNum;

        // 从已审核的流程查找下一个审核流程
        int tempValue = curAuditProcessBitMark >> prevBitNum;
        if (tempValue == 0) {
            // 说明下一个流程为审核完成，即当前流程为最后一个审核流程
            return 0;
        }

        int bitNumOffset = 1;
        while (tempValue != 0) {
            tempValue >>= 1;
            if ((tempValue & 1) == 1) {
                bitNumOffset++;
                break;
            }
        }

        return nextBitNum + bitNumOffset;
    }
}
