package com.wuhui.insert;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReadJsonFileToSqlFile {

    public static void main(String[] args) throws IOException {
        String path = Objects.requireNonNull(ReadFileConvertToJsonWriteToOutPutFile.class.getClassLoader().getResource("weighbuyjson.txt")).getPath();
        List<String> jsonList = Lists.newArrayList();
        FileReader fr = null;
        try {
            fr = new FileReader(path);
            BufferedReader br =  new BufferedReader(fr);

            String json;
            while ((json = br.readLine()) != null) {
                System.out.println(json);
                jsonList.add(json);
            }
        } catch (Exception e) {
            // ignore
        } finally {
            if (fr != null) {
                fr.close();
            }
        }
        System.out.println("json的数量:" + jsonList.size());

        List<WeighBuyProduct> allWeighBuyProducts = Lists.newArrayList();
        for (String json : jsonList) {
            List<WeighBuyProduct> weighBuyProducts = JSON.parseArray(json, WeighBuyProduct.class);
            if (weighBuyProducts != null && !weighBuyProducts.isEmpty()) {
                allWeighBuyProducts.addAll(weighBuyProducts);
            }
        }

        if (allWeighBuyProducts.isEmpty()) {
            return;
        }

        System.out.println("称重商品的数量:" + allWeighBuyProducts.size());

        // 按id生序
        allWeighBuyProducts = allWeighBuyProducts.stream().sorted(Comparator.comparing(WeighBuyProduct::getId)).collect(Collectors.toList());

        // 创建输出文件
        File writeFile = new File("output.txt");
        if (writeFile.exists()) {
            writeFile.createNewFile();
        }
        FileWriter bw = new FileWriter(writeFile);

        int executeCount = 0;
        for (WeighBuyProduct weighBuyProduct : allWeighBuyProducts) {
            // 每100条数据为一批
            if (executeCount % 100 == 0) {
                if (executeCount != 0) {
                    String sleepSql = createSleepSql();
                    bw.write(sleepSql);
                }
            }

            String updateStatementSql = createUpdateStatementSql(weighBuyProduct);
            bw.write(updateStatementSql);

            // 增加执行次数
            executeCount++;
        }

        // 关闭文件流
        bw.close();
    }

    private static String createSleepSql() {
        return "SELECT SLEEP(2);\n"; // es refresh 1s 保证大于refresh的时间，降低io
    }

    private static String createUpdateStatementSql(WeighBuyProduct weighBuyProduct) {
        return "update `pupu_main`.`products` set `weigh_buy_max_weight` = " + weighBuyProduct.getWeighBuyMaxWeight() + " where `id` = '" + weighBuyProduct.getId() + "';\n";
    }
}

@Data
class WeighBuyProduct {

    private String id;

    private Integer weighBuyMaxWeight;
}
