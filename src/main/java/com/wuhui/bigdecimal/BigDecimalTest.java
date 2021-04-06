package com.wuhui.bigdecimal;

import java.math.BigDecimal;

public class BigDecimalTest {

    public static void main(String[] args) {
        final String weighBuyPrice = calculateWeighBuyPrice(800, 100, 250);
        System.out.println(weighBuyPrice);

        double divide = BigDecimal.valueOf(810).divide(BigDecimal.valueOf(100), BigDecimal.ROUND_DOWN).doubleValue();

        double a = 1780D / 100D;
        System.out.println(divide);

        final String weighBuyPriceText = buildWeighBuyPriceText(800, 100, 250);
        System.out.println(weighBuyPriceText);
    }

    private static String calculateWeighBuyPrice(Integer price, Integer weighBuyMaxWeight, Integer weighBuyPriceUnit) {
        if (price == null || price <= 0) {
            return "0";
        }
        if (weighBuyMaxWeight == null || weighBuyMaxWeight <= 0) {
            return "0";
        }
        if (weighBuyPriceUnit == null || weighBuyPriceUnit <= 0) {
            return "0";
        }


        return BigDecimal.valueOf(price).divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(weighBuyPriceUnit)).divide(BigDecimal.valueOf(weighBuyMaxWeight), 2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    private static String buildWeighBuyPriceText(Integer price, Integer weighBuyMaxWeight, Integer weighBuyPriceUnit) {
        if (price == 0) {
            return "";
        }
        if (weighBuyMaxWeight == null || weighBuyMaxWeight <= 0) {
            return "";
        }
        if (weighBuyPriceUnit == null || weighBuyPriceUnit <= 0) {
            return "";
        }

        // 计算称重商品的斤价分单位(售价*斤价单位/称重最大重量),向上取整
        int weighBuyPrice = BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(weighBuyPriceUnit)).divide(BigDecimal.valueOf(weighBuyMaxWeight), 0, BigDecimal.ROUND_HALF_UP).intValue();

        return "¥" + BigDecimal.valueOf(weighBuyPrice / 100D).stripTrailingZeros().toPlainString() + "/" + weighBuyPriceUnit + "g";
    }

}
