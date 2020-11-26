package com.wuhui.insert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReadFileToSqlFile {


    private static class TaxCategory {
        private String taxCategoryId;
        private Integer showType;

        public TaxCategory(String taxCategoryId, Integer showType) {
            this.taxCategoryId = taxCategoryId;
            this.showType = showType;
        }

        public Integer getShowType() {
            return showType;
        }

        public String getTaxCategoryId() {
            return taxCategoryId;
        }
    }

    private static List<TaxCategory> taxCategoryList;

    private static Map<String, TaxCategory> taxCategoryMap;

    private static Map<String, String> productToTaxCategoryMap;

    static {
        taxCategoryList = new ArrayList<>();
        TaxCategory taxCategory1 = new TaxCategory("1c6271eb-1508-4ce2-925a-4719ce1ca674", 0);
        taxCategoryList.add(taxCategory1);
        TaxCategory taxCategory2 = new TaxCategory("a7fd43cf-e867-4dee-8a46-02249db25f6d", 0);
        taxCategoryList.add(new TaxCategory("a7fd43cf-e867-4dee-8a46-02249db25f6d", 0));
        TaxCategory taxCategory3 = new TaxCategory("cd83184a-8400-4f3e-9485-342b227aa1d6", 0);
        taxCategoryList.add(new TaxCategory("cd83184a-8400-4f3e-9485-342b227aa1d6", 0));
        TaxCategory taxCategory4 = new TaxCategory("edbd1bfd-57bf-4571-a466-3c8856180fe3", -1);
        taxCategoryList.add(new TaxCategory("edbd1bfd-57bf-4571-a466-3c8856180fe3", -1));

        taxCategoryMap = new HashMap<>();
        taxCategoryMap.put("1c6271eb-1508-4ce2-925a-4719ce1ca674", taxCategory1);
        taxCategoryMap.put("a7fd43cf-e867-4dee-8a46-02249db25f6d", taxCategory2);
        taxCategoryMap.put("cd83184a-8400-4f3e-9485-342b227aa1d6", taxCategory3);
        taxCategoryMap.put("edbd1bfd-57bf-4571-a466-3c8856180fe3", taxCategory4);

        productToTaxCategoryMap = new HashMap<>();
        // 读取product文件，转换成map。productid, taxcategoryid
        String path = Objects.requireNonNull(ReadFileToSqlFile.class.getClassLoader().getResource("product.txt")).getPath();

        FileReader fr = null;
        try {
            fr = new FileReader(path);
            BufferedReader br =  new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                String[] s = str.split("\\s+");
                String productid = s[0];
                String taxcategoryid = s[1];
                productToTaxCategoryMap.put(productid, taxcategoryid);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * (`id`, `product_id`, `store_id`, `expect_arrival_time`, `time_create`, `time_update`, `tax_category_id`, `expect_arrival_time_show_type`, `max_stock_quantity`, `store_warning_stock_quantity`) values
     * ('6edb2902-6fdd-4d7f-935a-2ca2b0417660', '10fa651e-501b-4a8e-8df1-01d25351a964', '00b9eed9-1097-456c-a801-7292c71bd176', 0, 1597115441056, 1597115441056, 'cd83184a-8400-4f3e-9485-342b227aa1d', 0, 0, 0),
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // 读取txt文件，txt文件的每一行为 storeproductid，productid，storeid，timecreate
        String path = Objects.requireNonNull(ReadFileToSqlFile.class.getClassLoader().getResource("insert.txt")).getPath();

        FileReader fr = new FileReader(path);
        BufferedReader br =  new BufferedReader(fr);

        // 创建输出文件
        File writeFile = new File("output.txt");
        if (writeFile.exists()) {
            writeFile.createNewFile();
        }
        FileWriter bw = new FileWriter(writeFile);

        int executeCount = 0;
        String str;
        while ((str = br.readLine()) != null) {
            // 以10个为准，对插入数据进行拆分
            if (executeCount % 10 == 0) {
                // insert into `pupu_main`.`storeproductex` (`id`, `product_id`, `store_id`, `expect_arrival_time`, `time_create`, `time_update`, `tax_category_id`, `expect_arrival_time_show_type`, `max_stock_quantity`, `store_warning_stock_quantity`) values
                String insertStatementPrefix = createInsertStatementPrefix();
                bw.write(insertStatementPrefix);
            }

            String[] s = str.split("\\s+");
            String storeproductid = s[0];
            String productid = s[1];
            String storeid = s[2];
            String timecreate = s[3];

            String insertSqlvalues = createInsertSqlValues(storeproductid, productid, storeid, timecreate);

            bw.write(insertSqlvalues);

            // 增加执行次数
            executeCount++;
        }

        bw.close();
        br.close();
    }

    private static String createInsertStatementPrefix() {
        StringBuilder sb = new StringBuilder();

        sb.append("insert into `pupu_main`.`storeproductex` (`id`, `product_id`, `store_id`, `expect_arrival_time`, `time_create`, `time_update`, `tax_category_id`, `expect_arrival_time_show_type`, `max_stock_quantity`, `store_warning_stock_quantity`)")
                .append("\n")
                .append("values ");

        return sb.toString();
    }

    private static String createInsertSqlValues(String storeproductid, String productid, String storeid,
                                                String timecreate) {
        StringBuilder sb = new StringBuilder();

        sb.append("('")
                .append(storeproductid)
                .append("', '")
                .append(productid)
                .append("', '")
                .append(storeid)
                .append("', ")
                .append("0")
                .append(", ")
                .append(timecreate)
                .append(", ")
                .append(timecreate)
                .append(", '")
                .append(taxCategoryMap.get(productToTaxCategoryMap.get(productid)).getTaxCategoryId())
                .append("', ")
                .append(taxCategoryMap.get(productToTaxCategoryMap.get(productid)).getShowType())
                .append(", ")
                .append("0")
                .append(", ")
                .append("0")
                .append("),\n");

        return sb.toString();
    }

}
