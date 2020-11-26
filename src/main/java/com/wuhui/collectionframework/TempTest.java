package com.wuhui.collectionframework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TempTest {

    public static void main(String[] args) {
        List<String> oldIds = Arrays.asList("8291f402-d4b4-4eaa-b285-c09efdf6e039");
        List<String> deleteIds = new ArrayList<>();
        List<String> addIds = new ArrayList<>();
        System.out.println(isUpload(oldIds, deleteIds, addIds));
    }

    private static boolean isUpload(List<String> oldIds, List<String> deleteIds, List<String> addIds) {
        List<String> licenceCategoryList = new ArrayList<>();
        licenceCategoryList.add("8291f402-d4b4-4eaa-b285-c09efdf6e039");
        licenceCategoryList.add(null);
        if (isEmpty(licenceCategoryList)) {
            return true;
        }

        List<String> newProductLicenceCategoryIds = new ArrayList<>();
        // 计算出更新后剩余的证照类型id集合
        deleteIds.forEach(oldIds::remove);
        newProductLicenceCategoryIds.addAll(oldIds);
        newProductLicenceCategoryIds.addAll(addIds);

        List<String> filterResultListByNewProductLicenceIds = licenceCategoryList.stream()
                .filter(id ->
                        !newProductLicenceCategoryIds.contains(id))
                .collect(Collectors.toList());
        if (!isEmpty(filterResultListByNewProductLicenceIds)) {
            return false;
        }

        return true;
    }

    private static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }
}
