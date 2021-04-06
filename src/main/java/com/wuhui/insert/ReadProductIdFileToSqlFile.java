package com.wuhui.insert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadProductIdFileToSqlFile {

    /**
     * SELECT `id` FROM `pupu_main`.`products` WHERE `is_weigh_buy` = 0 AND `is_show_weigh_buy` = 1
     * UPDATE `pupu_main`.`products` SET `is_show_weigh_buy_price` = 0 WHERE `id` = '' and `is_weigh_buy` = 0 and `is_show_weigh_buy` = 1;
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // 读取txt文件。txt文件的每一行为 productid
        String path = ReadProductIdFileToSqlFile.class.getClassLoader().getResource("productid.txt").getPath();

        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        // 创建输出文件
        File outputFile = new File("output.txt");
        if (outputFile.exists()) {
            outputFile.createNewFile();
        }

        FileWriter writer = new FileWriter(outputFile);

        int executeCount = 0;
        String str;
        while ((str = br.readLine()) != null) {
            // 每执行1000条update。sleep一会儿
            if (executeCount != 0 && executeCount % 50 == 0) {
                String selectSleepSql = createSelectSleepSql();
                writer.write(selectSleepSql);
            }

            String productId = str;

            String updateSql = createUpdateSql(productId);

            writer.write(updateSql);

            // 增加执行次数
            executeCount++;
        }

        writer.close();
        br.close();
    }

    private static String createSelectSleepSql() {
        return "SELECT SLEEP(2);\n";
    }

    private static String createUpdateSql(String productId) {
        // UPDATE `pupu_main`.`products` SET `is_show_weigh_buy_price` = 0 WHERE `product_id` = '' and `is_weigh_buy` = 0 and `is_show_weigh_buy` = 1;
        StringBuilder builder = new StringBuilder();

        builder.append("UPDATE `products` SET `is_show_weigh_buy_price` = ")
                .append(0)
                .append(" WHERE `id` = '")
                .append(productId)
                .append("' and `is_weigh_buy` = 0")
                .append(" and `is_show_weigh_buy_price` = 1;\n");

        return builder.toString();
    }
}
