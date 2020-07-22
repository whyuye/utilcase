package com.wuhui.reflect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 建档采购请求实体，该类的实体规则是按前端页面给的参数来定的
 * 从左往右，上到下，以下实体是有顺序的，请维护的同学，参考前端页面排序
 *
 * @author wst
 * @date 2019-06-11 09:44
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilingDTO implements Serializable {

    private int num;

    private String id;

    private List<String> categoryIds;

    private List<String> categoryNames;

    private ProductFlagDataDTO productFlagData;

    private ProductExDTO productEx;

    private List<FilingAreaProductDTO> areaProductList;

    private List<FilingTaxCategoryDTO> taxCategoryList;

    public static void main(String[] args) {

        ProductFilingDTO productFilingDTO = new ProductFilingDTO();
        System.out.println(haveIdField(productFilingDTO));

    }

    public static boolean haveIdField(Object object) {
        try {
            object.getClass().getDeclaredField("id");
            Field[] declaredFields = object.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                System.out.println(field.getType());
            }
            return true;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        }
    }
}

class ProductFlagDataDTO {

    private String id;
}

class ProductExDTO {

    private String id;
}

class FilingAreaProductDTO {

    private String id;

}

class FilingTaxCategoryDTO {

    private String id;
}
