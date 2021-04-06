package com.wuhui.insert;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReadInventoryVerificationTemplatesFile {

    public static void main(String[] args) throws IOException {
        String path = ReadInventoryVerificationTemplatesFile.class.getClassLoader().getResource("inventoryverificationtemplates.txt").getPath();

        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        // 创建输出文件
        File outputFile = new File("output.txt");
        if (outputFile.exists()) {
            outputFile.createNewFile();
        }

        FileWriter writer = new FileWriter(outputFile);

        String str = br.readLine();
        if (str == null) {
            return;
        }

        List<templateIdObj> templateIdObjects = JSON.parseArray(str, templateIdObj.class);
        System.out.println(templateIdObjects);

        // 按id升序
        List<templateIdObj> sortedTemplateIdObjects = templateIdObjects.stream().sorted(Comparator.comparing(templateIdObj::getId)).collect(Collectors.toList());

        int executeCount = 0;
        for (templateIdObj templateIdObj : sortedTemplateIdObjects) {
            // 每执行100条update。sleep一会儿
            if (executeCount != 0 && executeCount % 100 == 0) {
                String selectSleepSql = createSleepSql();
                writer.write(selectSleepSql);
            }

            String updateTemplateStatusSql = createUpdateTemplateStatusSql(templateIdObj.getId());
            writer.write(updateTemplateStatusSql);

            // 增加执行次数
            executeCount++;
        }

        writer.close();
        br.close();
    }

    private static String createUpdateTemplateStatusSql(String id) {
        StringBuilder sb = new StringBuilder();

        sb.append("UPDATE `pupu_main`.`inventoryverificationtemplates` SET status = 2 WHERE `id` = '")
                .append(id)
                .append("';\n");

        return sb.toString();
    }

    private static String createSleepSql() {
        return "SELECT SLEEP(1);\n"; // es refresh 1s 保证大于refresh的时间，降低io
    }
}

@Data
class templateIdObj {

    private String id;
}