package com.wuhui.insert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ReadProductIdFileToSqlFile {

    public static void main(String[] args) throws Exception {
        // 读取txt文件。txt文件的每一行为 productid box_regulation(带有单位的整箱箱规)
        String path = ReadProductIdFileToSqlFile.class.getClassLoader()
                .getResource("1010384.txt").getPath();

        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        // 创建输出文件
        File outputFile = new File("output.txt");
        if (outputFile.exists()) {
            outputFile.createNewFile();
        }

        FileWriter writer = new FileWriter(outputFile);

        String str;
        while ((str = br.readLine()) != null) {
            String productId = str;

            String updateSql = createUpdateProductAuditSql(productId);

            String deleteSql = createDeleteProductAuditExSql(productId);

            writer.write(deleteSql);
            writer.write(updateSql);
        }

        writer.close();
        br.close();
    }

    /**
     * #更新订货合规为待审核
     * update `productaudit` set `purchase_compliance_status` = 1 where `product_id` = ''
     *
     * @param productId
     * @return
     */

    private static String createUpdateProductAuditSql(String productId) {
        return "update `productaudit` set `purchase_compliance_status` = 1 where `product_id` = '" + productId + "';\n";
    }

    /**
     * #删除订货合规审核的完成记录
     * delete from `productauditex` where `product_id` = '' and `change_data_type` = 8
     *
     * @param productId
     * @return
     */
    private static String createDeleteProductAuditExSql(String productId) {
        return "delete from `productauditex` where `product_id` = '" + productId + "' and `change_data_type` = 8;\n";
    }
}
