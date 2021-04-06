package com.wuhui.reflect;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectTest7 {

    public static void main(String[] args) {
        StoreProductNativeProjection storeProductNativeProjection = new StoreProductNativeProjection() {
            @Override
            public String getId() {
                return "1111";
            }

            @Override
            public String getProductId() {
                return "2222";
            }

            @Override
            public Integer getStockQuantity() {
                return 66;
            }
        };

        System.out.println(storeProductNativeProjection);

        StoreProductDTO newStoreProductDTO = new StoreProductDTO();
        System.out.println(newStoreProductDTO);

        System.out.println(storeProductNativeProjection.getId());

        StoreProductDTO storeProductDTO = cloneObject(storeProductNativeProjection, StoreProductDTO.class);
        System.out.println(storeProductDTO);
    }

    public static <T> T cloneObject(Object objectFrom, Class<T> toClazz) {
        if (objectFrom == null) {
            return null;
        } else {
            try {
                T t = toClazz.newInstance();
                Field[] fields = toClazz.getDeclaredFields();
                Field[] var4 = fields;
                int var5 = fields.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Field field = var4[var6];
                    field.setAccessible(true);
                    if (!Modifier.isFinal(field.getModifiers())) {
                        String propertyName = field.getName();

                        Field fieldFrom;
                        try {
                            fieldFrom = objectFrom.getClass().getDeclaredField(propertyName);
                        } catch (NoSuchFieldException var11) {
                            continue;
                        }

                        fieldFrom.setAccessible(true);
                        Object value = fieldFrom.get(objectFrom);
                        field.set(t, value);
                    }
                }

                return t;
            } catch (IllegalAccessException | InstantiationException var12) {
                return null;
            }
        }
    }

    public interface StoreProductNativeProjection {

        String getId();

        String getProductId();

        Integer getStockQuantity();
    }

    @Data
    public static class StoreProductDTO {

        private String id;

        private String productId;

        private Integer stockQuantity;
    }
}
