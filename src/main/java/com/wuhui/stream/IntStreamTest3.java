package com.wuhui.stream;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class IntStreamTest3 {

    public static void main(String[] args) {

        StoreProductDTO storeProductDTO1 = new StoreProductDTO();
        storeProductDTO1.setStockQuantity(1);
        StoreProductDTO storeProductDTO2 = new StoreProductDTO();
        storeProductDTO2.setStockQuantity(2);
        StoreProductDTO storeProductDTO3 = new StoreProductDTO();
        storeProductDTO3.setStockQuantity(3);
        StoreProductDTO storeProductDTO4 = new StoreProductDTO();
        storeProductDTO4.setStockQuantity(4);
        StoreProductDTO storeProductDTO5 = new StoreProductDTO();
        storeProductDTO5.setStockQuantity(5);
        StoreProductDTO storeProductDTO6 = new StoreProductDTO();
        storeProductDTO6.setStockQuantity(6);

        List<StoreProductDTO> storeProductDTOList = new ArrayList<>();
        storeProductDTOList.add(storeProductDTO1);
        storeProductDTOList.add(storeProductDTO2);
        storeProductDTOList.add(storeProductDTO3);
        storeProductDTOList.add(storeProductDTO4);
        storeProductDTOList.add(storeProductDTO5);
        storeProductDTOList.add(storeProductDTO6);

        Optional<StoreProductDTO> max = storeProductDTOList.stream()
                .max(Comparator.comparing(StoreProductDTO::getStockQuantity));

        System.out.println(max.map(storeProductDTO -> "最大值:" + storeProductDTO.getStockQuantity()).orElse("不存在最大值"));

        StoreProductDTO storeProductDTO7 = new StoreProductDTO();
        System.out.println(storeProductDTO7);
    }
}

@ToString
@Getter
@Setter
class StoreProductDTO {

    Integer stockQuantity;

    int primitiveStockQuantity;
}