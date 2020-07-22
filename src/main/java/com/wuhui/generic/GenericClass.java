package com.wuhui.generic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GenericClass {

    public static void main(String[] args) {

    }

//    public static <T, ID> List<T> sortList(List<T> entityList, List<ID> idList) throws NoSuchFieldException, IllegalAccessException {
//        if (idList != null && !idList.isEmpty() && entityList != null && !entityList.isEmpty()) {
//            Map<ID, Integer> idMap = listToSortedMap(idList);
//            Map<Integer, T> entityMap = new TreeMap<>();
//
//            for(int i = 0; i < entityList.size(); ++i) {
//                T entity = entityList.get(i);
//                if (entity != null) {
//                    ID id = getId(entity);
//                    if (id != null) {
//                        int j = (Integer)idMap.get(id);
//                        entityMap.put(j, entity);
//                    }
//                }
//            }
//
//            return new ArrayList<>(entityMap.values());
//        } else {
//            return Collections.emptyList();
//        }
//    }
//
//    public static <T, I> I getId(T entity) throws NoSuchFieldException, IllegalAccessException {
//        Field field = entity.getClass().getDeclaredField("id");
//        Object id = null;
//        id = field.get(entity);
//        return (I) id;
//    }
}
