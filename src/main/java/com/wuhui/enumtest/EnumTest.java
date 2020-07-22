package com.wuhui.enumtest;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumTest {

    public static void main(String[] args) {

        System.out.println(CommonCityContents.of("coupon_flash_sale_info").getDesc());
    }
}

enum CommonCityContents {

    /**
     * 命名规则为：
     * 业务名称_XXX
     * <p>
     * 例如：
     * order_logistics_name
     * product_logistics_name
     * coupon_city_info
     */
    coupon_flash_sale_info("限时抢购不可用券提示文案"),

    coupon_day_use_info("优惠券每日使用张数文案");

    private String desc;

    private static Map<String, CommonCityContents> VALUES;

    CommonCityContents(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    static {
        VALUES = Collections.unmodifiableMap(Stream.of(values()).collect(Collectors.toMap(Enum::name, Function.identity())));
    }

    public static CommonCityContents of(String name) {
        return VALUES.get(name);
    }
}