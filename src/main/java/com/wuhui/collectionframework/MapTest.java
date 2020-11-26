package com.wuhui.collectionframework;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        map.put("hello", "world");

        System.out.printf(map.keySet().toString());
    }
}
