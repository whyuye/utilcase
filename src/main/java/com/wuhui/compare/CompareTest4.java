package com.wuhui.compare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CompareTest4 {

    public static void main(String[] args) {
        List<Product> products = initProducts();

        // 按code倒序后，再按保质期倒序
        List<Product> sortedProducts = products.stream()
                //.sorted(Comparator.comparing(Product::getProductCode).reversed()
                //                        .thenComparing((c1, c2) -> c2.getProductShelfLifeValue().compareTo(c1.getProductShelfLifeValue())))
                // 和下面这种排序方式达到的效果是一样的，先整体顺序，最后整体倒序
                .sorted(Comparator.comparing(Product::getProductCode).thenComparing(Product::getProductShelfLifeValue).reversed())
                .collect(Collectors.toList());

        System.out.println(sortedProducts);
    }


    private static List<Product> initProducts() {
        Product product1 = new Product(1, 3);
        Product product2 = new Product(2, 2);
        Product product3 = new Product(3, 1);
        Product product4 = new Product(4, 1);
        Product product5 = new Product(5, 2);
        Product product6 = new Product(5, 1);
        Product product7 = new Product(6, 6);
        Product product8 = new Product(6, 2);

        return Arrays.asList(product2, product1, product3, product4, product8, product7, product5, product6);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Product {

    private Integer productCode;

    private Integer productShelfLifeValue;
}
