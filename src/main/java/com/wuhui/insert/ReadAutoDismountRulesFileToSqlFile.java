package com.wuhui.insert;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReadAutoDismountRulesFileToSqlFile {

    public static void main(String[] args) throws IOException {
        String path = Objects.requireNonNull(ReadFileToSqlFile.class.getClassLoader().getResource("autodismountrules.txt")).getPath();

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

        List<ProductAutoProcessingTemplate> productAutoProcessingTemplateList = Lists.newArrayList();
        while ((str = br.readLine()) != null) {
            String[] line = str.split(",");
            // id,rule_num,box_product_id,single_product_id,box_unit_count,act_stock,box_process_count,time_create,time_update,user_id_create,user_id_update,box_product_stock_threshold
            String id = line[0];
            if ("\uFEFFid".equals(id)) {
                // 首行
                continue;
            }
            String rule_num = line[1];
            String box_product_id = line[2];
            String single_product_id = line[3];
            String box_unit_count = line[4];
            String act_stock = line[5];
            String box_process_count = line[6];
            String time_create = line[7];
            String time_update = line[8];
            String user_id_create = line[9];
            String user_id_update = line[10];
            String box_product_stock_threshold = line[11];

            final ProductAutoProcessingTemplate productAutoProcessingTemplate = ProductAutoProcessingTemplate.builder()
                    .id(id)
                    .timeCreate(Long.parseLong(time_create))
                    .timeUpdate(Long.parseLong(time_update))
                    .userIdCreate(user_id_create)
                    .userIdUpdate(user_id_update)
                    .templateNum(Integer.parseInt(rule_num))
                    .code(1801) // 整拆散
                    .status(2) // 审核通过
                    .effectStoreScope(1) // 不限门店
                    .remark("")
                    .inItemProductId(box_product_id)
                    .outItemProductId(single_product_id)
                    .inItemStockThreshold(Integer.parseInt(box_product_stock_threshold))
                    .outItemStockThreshold(Integer.parseInt(act_stock))
                    .inItemUnitCount(Integer.parseInt(box_unit_count))
                    .inItemProcessCount(Integer.parseInt(box_process_count))
                    .build();

            productAutoProcessingTemplateList.add(productAutoProcessingTemplate);
        }
        fr.close();

        // 按Id顺序插入，减少脏页的影响
        List<ProductAutoProcessingTemplate> sortedByIdList = productAutoProcessingTemplateList.stream().sorted(Comparator.comparing(ProductAutoProcessingTemplate::getId)).collect(Collectors.toList());
        for (ProductAutoProcessingTemplate productAutoProcessingTemplate : sortedByIdList) {
            // 每100条数据为一批
            if (executeCount % 100 == 0) {
                if (executeCount != 0) {
                    String sleepSql = createSleepSql();
                    bw.write(sleepSql);
                }
            }

            String insertStatementPrefix = createInsertStatementPrefix();
            bw.write(insertStatementPrefix);

            String insertSqlValues = createInsertSqlValues(productAutoProcessingTemplate);
            bw.write(insertSqlValues);

            // 执行次数加1
            executeCount++;
        }

        // 关闭流
        bw.close();
    }

    private static String createInsertSqlValues(ProductAutoProcessingTemplate productAutoProcessingTemplate) {
        StringBuilder sb = new StringBuilder();
        sb.append("('")
                .append(productAutoProcessingTemplate.getId())
                .append("', ")
                .append(productAutoProcessingTemplate.getTemplateNum())
                .append(", ")
                .append(productAutoProcessingTemplate.getCode())
                .append(", ")
                .append(productAutoProcessingTemplate.getStatus())
                .append(", ")
                .append(productAutoProcessingTemplate.getEffectStoreScope())
                .append(", '")
                .append(productAutoProcessingTemplate.getRemark())
                .append("', '")
                .append(productAutoProcessingTemplate.getInItemProductId())
                .append("', '")
                .append(productAutoProcessingTemplate.getOutItemProductId())
                .append("', ")
                .append(productAutoProcessingTemplate.getInItemStockThreshold())
                .append(", ")
                .append(productAutoProcessingTemplate.getOutItemStockThreshold())
                .append(", ")
                .append(productAutoProcessingTemplate.getInItemUnitCount())
                .append(", ")
                .append(productAutoProcessingTemplate.getInItemProcessCount())
                .append(", ")
                .append(productAutoProcessingTemplate.getTimeCreate())
                .append(", ")
                .append(productAutoProcessingTemplate.getTimeUpdate())
                .append(", '")
                .append(productAutoProcessingTemplate.getUserIdCreate())
                .append("', '")
                .append(productAutoProcessingTemplate.getUserIdUpdate())
                .append("');\n");

        return sb.toString();
    }

    private static String createInsertStatementPrefix() {
        StringBuilder sb = new StringBuilder(); // StringBuilder是线程不安全的

        sb.append("insert into `pupu_main`.`productautoprocessingtemplates` (`id`, `template_num`, `code`, `status`, `effect_store_scope`, `remark`, `in_item_product_id`, `out_item_product_id`, `in_item_stock_threshold`, `out_item_stock_threshold`, `in_item_unit_count`, `in_item_process_count`, `time_create`, `time_update`, `user_id_create`, `user_id_update`)")
                .append(" values ");

        return sb.toString();
    }

    private static String createSleepSql() {
        return "SELECT SLEEP(1);\n";
    }

}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ProductAutoProcessingTemplate {

    private String id;

    private Long timeCreate;

    private Long timeUpdate;

    private String userIdCreate;

    private String userIdUpdate;

    /**
     * 模板编号
     */
    private Integer templateNum;

    /**
     * 加工类型 dynamicgenerictype。不存id的原因是减少所以索引占用的空间，code也可以做为唯一的标识
     */
    private Integer code;

    /**
     * 模板状态。 1.待审核 2.审核通过 3.审核拒绝 4.已停用 enum
     */
    private Integer status;

    /**
     * 生效门店范围: 1.不限门店 2.指定城市 3.指定门店
     */
    private Integer effectStoreScope;

    /**
     * 备注信息
     */
    private String remark;

    /* 以下字段为原料商品和产出商品的具体配置信息 **/

    /**
     * 原料商品ID
     */
    private String inItemProductId;

    /**
     * 产出商品ID
     */
    private String outItemProductId;

    /**
     * 触发整拆散的阈值，仅当母品库存大于它时，才进行整拆散
     */
    private Integer inItemStockThreshold;

    /**
     * 触发库存数<br>
     * 当产出商品库存小于等于多少时，触发自动加工
     */
    private Integer outItemStockThreshold;

    /**
     * 原料商品转化率，加工成多少份
     */
    private Integer inItemUnitCount;

    /**
     * 加工原料数<br>
     * 把多少原料商品进行加工<br>
     * 如果实际库存小于该值 ，则有多少加工多少
     */
    private Integer inItemProcessCount;
}