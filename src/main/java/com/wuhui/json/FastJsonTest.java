package com.wuhui.json;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastJsonTest {

    public static void main(String[] args) {
        InventoryVerificationScopeRO inventoryVerificationScopeRO1 = new InventoryVerificationScopeRO();
        inventoryVerificationScopeRO1.setConditionType(1);
        inventoryVerificationScopeRO1.setMinPriceGuide(100);
        inventoryVerificationScopeRO1.setMaxPriceGuide(200);

        InventoryVerificationScopeRO inventoryVerificationScopeRO2 = new InventoryVerificationScopeRO();
        inventoryVerificationScopeRO2.setConditionType(2);
        inventoryVerificationScopeRO2.setTaxCategoryIds(Lists.newArrayList("1a", "2a", "3", "66"));

        final String jsonString1 = JSON.toJSONString(inventoryVerificationScopeRO1);
        System.out.println(jsonString1);

        final String jsonString2 = JSON.toJSONString(inventoryVerificationScopeRO2);
        System.out.println(jsonString2);

        final InventoryVerificationScopeRO inventoryVerificationScopeRO3 = JSON.parseObject(jsonString1, InventoryVerificationScopeRO.class);
        System.out.println(inventoryVerificationScopeRO3);

        final InventoryVerificationScopeRO inventoryVerificationScopeRO4 = JSON.parseObject(jsonString2, InventoryVerificationScopeRO.class);
        System.out.println(inventoryVerificationScopeRO4);

        System.out.println("======要六六大顺噢😄，nice～");

        InventoryVerificationScopeRO inventoryVerificationScopeRO5 = new InventoryVerificationScopeRO();
        inventoryVerificationScopeRO5.setConditionType(6);
        inventoryVerificationScopeRO5.setTestJsonToString("123");
        final NestedRO nestedRO = new NestedRO();
        nestedRO.setNestedInteger(666);
        inventoryVerificationScopeRO5.setNestedRO(nestedRO);

        final String jsonString3 = JSON.toJSONString(inventoryVerificationScopeRO5);
        System.out.println(jsonString3);

        final InventoryVerificationScopeRO inventoryVerificationScopeRO6 = JSON.parseObject(jsonString3, InventoryVerificationScopeRO.class);
        System.out.println(inventoryVerificationScopeRO6);

        Map<String, Integer> map = new HashMap<String, Integer>() {{
            put("hello", 1);
            put("world", 2);
        }};

        final String mapToJson = JSON.toJSONString(map);
        System.out.println(mapToJson);

        System.out.println(JSON.toJSONString(Maps.newHashMap()));

        System.out.println(JSON.toJSONString(inventoryVerificationScopeRO5.getTestJsonToString()));
    }
}

@Data
class InventoryVerificationScopeRO {

    private Integer conditionType;

    private List<String> taxCategoryIds;

    private List<String> shelfCodes;

    private List<String> productIds;

    private List<String> supplierIds;

    private Integer minStockQuantity;

    private Integer maxStockQuantity;

    private Integer minPriceGuide;

    private Integer maxPriceGuide;

    private Integer expiredDangerHourValueInside;

    private NestedRO nestedRO;

    private String testJsonToString;
}

@Data
class NestedRO {
    private Integer nestedInteger;
}
