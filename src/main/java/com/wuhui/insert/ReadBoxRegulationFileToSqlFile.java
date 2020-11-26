package com.wuhui.insert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadBoxRegulationFileToSqlFile {

    private static Pattern space_pattern = Pattern.compile("\\s+");

    private static Pattern int_pattern = Pattern.compile("\\d+");

    /**
     * SELECT `id`, `box_regulation` FROM `pupu_main`.`products`
     * UPDATE `pupu_main`.`productareas` SET `box_regulation` = WHERE `product_id` = '' and `box_regulation` = 0;
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // 读取txt文件。txt文件的每一行为 productid box_regulation(带有单位的整箱箱规)
        String path = ReadBoxRegulationFileToSqlFile.class.getClassLoader()
                .getResource("product_box_regulation.txt").getPath();

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
            if (executeCount != 0 && executeCount % 1000 == 0) {
                String selectSleepSql = createSelectSleepSql();
                writer.write(selectSleepSql);
            }

            String[] s = str.split(space_pattern.pattern());

            String productId = s[0];
            String boxRegulation = "";
            if (s.length == 2) {
                boxRegulation = s[1];
            }

            boxRegulation = normalizeProductRegulation(boxRegulation);

            String updateSql = createUpdateSql(productId, boxRegulation);

            writer.write(updateSql);

            // 增加执行次数
            executeCount++;
        }

        writer.close();
        br.close();
    }

    private static String createSelectSleepSql() {
        return "SELECT SLEEP(1);\n";
    }

    private static String createUpdateSql(String productId, String boxRegulation) {
        // UPDATE `pupu_main`.`productareas` SET `box_regulation` = WHERE `product_id` = '' and `box_regulation` = 0;
        StringBuilder builder = new StringBuilder();

        builder.append("UPDATE `pupu_main`.`productareas` SET `box_regulation` = ")
                .append(boxRegulation)
                .append(" WHERE `product_id` = '")
                .append(productId)
                .append("' and `box_regulation` = 0;\n");

        return builder.toString();
    }

    private static String normalizeProductRegulation(String boxRegulation) {
        if (boxRegulation == null || "".equals(boxRegulation.trim())) {
            return "0";
        }

        // 判断字符串是不是以数字开头
        Matcher firstCharMatcher = int_pattern.matcher(boxRegulation.charAt(0) + "");
        if (!firstCharMatcher.matches()) {
            return "0";
        }

        // 以字符串开头，截取所有整数前缀做为返回值
        Matcher strMatcher = int_pattern.matcher(boxRegulation);
        if (strMatcher.find()) {
            return strMatcher.group();
        }

        return "0";
    }
}
