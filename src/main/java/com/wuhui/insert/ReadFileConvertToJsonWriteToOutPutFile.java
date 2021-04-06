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

public class ReadFileConvertToJsonWriteToOutPutFile {

    private static final String INPUT_FILE_NAME = "json.txt";

    public static void main(String[] args) throws IOException {
        String path = Objects.requireNonNull(ReadFileConvertToJsonWriteToOutPutFile.class.getClassLoader().getResource("json.txt")).getPath();
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                fr.close();
            }
        }
        System.out.println("json的数量:" + jsonList.size());


        List<StockQuantityLogDTO> allStockQuantityLogDTOS = Lists.newArrayList();
        for (String json : jsonList) {
            List<StockQuantityLogDTO> stockQuantityLogDTOS = JSON.parseArray(json, StockQuantityLogDTO.class);
            if (stockQuantityLogDTOS != null && !stockQuantityLogDTOS.isEmpty()) {
                allStockQuantityLogDTOS.addAll(stockQuantityLogDTOS);
            }
        }

        if (allStockQuantityLogDTOS.isEmpty()) {
            return;
        }
        System.out.println("库存变更日志数量:" + allStockQuantityLogDTOS.size());
        // 按id生序
        allStockQuantityLogDTOS = allStockQuantityLogDTOS.stream().sorted(Comparator.comparing(StockQuantityLogDTO::getId)).collect(Collectors.toList());

        // 创建输出文件
        File writeFile = new File("output.txt");
        if (writeFile.exists()) {
            writeFile.createNewFile();
        }
        FileWriter bw = new FileWriter(writeFile);

        // insert ignore into `pupu_main`.`stockquantitylogs` (`id`, `store_id`, `product_id`, `store_product_id`, `batch_id`, `quantity_old`, `quantity_new`, `quantity_diff`, `change_type`, `origin`, `ref_type`, `time_create`, `time_update`, `user_id_update`, `store_product_quantity_old`, `store_product_quantity_new`, `store_product_exception_quantity_old`, `store_product_exception_quantity_new`) values ()

        int executeCount = 0;
        for (StockQuantityLogDTO stockQuantityLogDTO : allStockQuantityLogDTOS) {
            // 每10条数据为一批
            if (executeCount % 100 == 0) {
                if (executeCount != 0) {
                    String sleepSql = createSleepSql();
                    bw.write(sleepSql);
                }
            }

            String insertStatementPrefix = createInsertStatementPrefix();
            bw.write(insertStatementPrefix);
            String sql = createInsertSqlValues(stockQuantityLogDTO);
            bw.write(sql);

            // 增加执行次数
            executeCount++;
        }

        // 关闭文件流
        bw.close();
    }

    private static String createSleepSql() {
        return "SELECT SLEEP(2);\n"; // es refresh 1s 保证大于refresh的时间，降低io
    }

    private static String createInsertStatementPrefix() {
        StringBuilder sb = new StringBuilder();

        sb.append("insert ignore into `pupu_main`.`stockquantitylogs` (`id`, `store_id`, `product_id`, `store_product_id`, `batch_id`, `quantity_old`, `quantity_new`, `quantity_diff`, `change_type`, `origin`, `ref_type`, `ref_id`, `time_create`, `time_update`, `user_id_update`, `store_product_quantity_old`, `store_product_quantity_new`)")
                //.append("\n")
                .append(" values ");

        return sb.toString();
    }

    private static String createInsertSqlValues(StockQuantityLogDTO stockQuantityLogDTO) {
        StringBuilder sb = new StringBuilder();

        sb.append("('")
                .append(stockQuantityLogDTO.getId())
                .append("', '")
                .append(stockQuantityLogDTO.getStoreId())
                .append("', '")
                .append(stockQuantityLogDTO.getProductId())
                .append("', '")
                .append(stockQuantityLogDTO.getStoreProductId())
                .append("', '")
                .append(stockQuantityLogDTO.getBatchId())
                .append("', '")
                .append(stockQuantityLogDTO.getQuantityOld())
                .append("', '")
                .append(stockQuantityLogDTO.getQuantityNew())
                .append("', '")
                .append(stockQuantityLogDTO.getQuantityDiff())
                .append("', '")
                .append(stockQuantityLogDTO.getChangeType())
                .append("', '")
                .append(stockQuantityLogDTO.getOrigin())
                .append("', '")
                .append(stockQuantityLogDTO.getRefType())
                .append("', '")
                .append(stockQuantityLogDTO.getRefId())
                .append("', '")
                .append(stockQuantityLogDTO.getTimeCreate())
                .append("', '")
                .append(stockQuantityLogDTO.getTimeUpdate())
                .append("', '")
                .append(stockQuantityLogDTO.getUserIdUpdate())
                .append("', '")
                .append(stockQuantityLogDTO.getStoreProductQuantityOld())
                .append("', '")
                .append(stockQuantityLogDTO.getStoreProductQuantityNew())
                .append("');\n");

        return sb.toString();
    }
}

@Data
class StockQuantityLogDTO {

    private String id;
    private String storeId;
    private String productId;
    private String storeProductId;
    private String batchId;
    private Integer quantityOld;
    private Integer quantityNew;
    private Integer quantityDiff;
    private Integer changeType;
    private Integer origin;
    private Integer storeProductQuantityOld;
    private Integer storeProductQuantityNew;
    private String refId;
    private Integer refType = -1;
    private Long timeCreate;
    private Long timeUpdate;
    private String userIdUpdate;

}
