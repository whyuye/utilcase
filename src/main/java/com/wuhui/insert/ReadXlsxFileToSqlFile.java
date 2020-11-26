package com.wuhui.insert;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ReadXlsxFileToSqlFile {

    private static final Map<Integer, Map<Integer, AreaProductBoxRegulation>> code2ZipAreaProductBoxRegulationMap;

    private static final Map<Integer, String> code2ProductIdMap;

    private static final Map<String, Map<Integer, String>> productId2ZipAreaProductMap;

    static {
        // 读取excel文件，转换成map。key为商品编码，value为对应的城市商品
        String path1 = Objects.requireNonNull(ReadXlsxFileToSqlFile.class.getClassLoader().getResource("boxregulation2.txt")).getPath();
        code2ZipAreaProductBoxRegulationMap = Collections.unmodifiableMap(getBoxRegulationMapByExcelFilePath(path1));

        String path2 = Objects.requireNonNull(ReadXlsxFileToSqlFile.class.getClassLoader().getResource("products.txt")).getPath();
        code2ProductIdMap = Collections.unmodifiableMap(getCode2ProductIdMap(path2));

        String path3 = Objects.requireNonNull(ReadXlsxFileToSqlFile.class.getClassLoader().getResource("productareas.txt")).getPath();
        productId2ZipAreaProductMap = Collections.unmodifiableMap(getProductId2ZipAreaProductMap(path3));
    }

    private static Map<String, Map<Integer, String>> getProductId2ZipAreaProductMap(String path) {
        Map<String, Map<Integer, String>> resultMap = new HashMap<>();

        FileReader fr;
        BufferedReader br = null;
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            // productarea_id,product_id,area_zip
            String str;
            while ((str = br.readLine()) != null) {
                String[] line = str.split(",");

                String productAreaId = line[0];
                String productId = line[1];
                String areaZip = line[2];

                Map<Integer, String> integerStringMap = resultMap.putIfAbsent(productId, new HashMap<>());
                if (integerStringMap == null) {
                    resultMap.get(productId).put(Integer.parseInt(areaZip), productAreaId);
                } else {
                    integerStringMap.put(Integer.parseInt(areaZip), productAreaId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // ignore exception
                }
            }
        }

        return resultMap;
    }

    private static Map<Integer, String> getCode2ProductIdMap(String path) {
        Map<Integer, String> resultMap = new HashMap<>();

        FileReader fr;
        BufferedReader br = null;
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            // productid,code
            String str;
            while ((str = br.readLine()) != null) {
                String[] line = str.split(",");

                String productId = line[0];
                String code = line[1];

                resultMap.put(Integer.parseInt(code), productId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // ignore exception
                }
            }
        }

        return resultMap;
    }

    private static Map<Integer, Map<Integer, AreaProductBoxRegulation>> getBoxRegulationMapByExcelFilePath(String path) {

        FileReader fr;
        BufferedReader br = null;
        Map<Integer, Map<Integer, AreaProductBoxRegulation>> resultMap = new HashMap<>();

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                String[] line = str.split(",");

                String code = line[0];
                String fuZhouBoxRegualtion = "/".equals(line[3]) ? "0" : line[3];
                String xiaMenBoxRegualtion = "/".equals(line[4]) ? "0" : line[4];
                String shenZhenBoxRegualtion = "/".equals(line[5]) ? "0" : line[5];
                String guangZhouBoxRegualtion = "/".equals(line[6]) ? "0" : line[6];

                Map<Integer, AreaProductBoxRegulation> resultMapValue = createResultMapValue(fuZhouBoxRegualtion, xiaMenBoxRegualtion, shenZhenBoxRegualtion, guangZhouBoxRegualtion);

                resultMap.put(Integer.parseInt(code), resultMapValue);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // ignore exception
                }
            }
        }

        return resultMap;
    }

    private static Map<Integer, AreaProductBoxRegulation> createResultMapValue(String fuZhouBoxRegualtion, String xiaMenBoxRegualtion, String shenZhenBoxRegualtion, String guangZhouBoxRegualtion) {
        AreaProductBoxRegulation fuzhou = new AreaProductBoxRegulation(350100, Integer.parseInt(fuZhouBoxRegualtion.trim()));
        AreaProductBoxRegulation xiamen = new AreaProductBoxRegulation(350200, Integer.parseInt(xiaMenBoxRegualtion.trim()));
        AreaProductBoxRegulation shenzhen = new AreaProductBoxRegulation(440300, Integer.parseInt(shenZhenBoxRegualtion.trim()));
        AreaProductBoxRegulation guangzhou = new AreaProductBoxRegulation(440100, Integer.parseInt(guangZhouBoxRegualtion.trim()));

        Map<Integer, AreaProductBoxRegulation> resultMap = new HashMap<>((int)Math.ceil(4 * 4/3));

        resultMap.put(350100, fuzhou);
        resultMap.put(350200, xiamen);
        resultMap.put(440300, shenzhen);
        resultMap.put(440100, guangzhou);
        return resultMap;
    }

    @Data
    @AllArgsConstructor
    static class AreaProductBoxRegulation {
        private Integer areaZip;

        private Integer boxRegulation;

    }

    public static void main(String[] args) throws IOException {
        // 创建输出文件
        File outputFile = new File("output.txt");
        if (outputFile.exists()) {
            outputFile.createNewFile();
        }

        FileWriter writer = new FileWriter(outputFile);

        int updateCount = 1;
        for (Map.Entry<Integer, Map<Integer, AreaProductBoxRegulation>> entry : code2ZipAreaProductBoxRegulationMap.entrySet()) {
            Integer code = entry.getKey();
            Map<Integer, AreaProductBoxRegulation> curAreaProductBoxRegulationMap = entry.getValue();

            String productId = code2ProductIdMap.get(code);

            Map<Integer, String> zip2AreaProductIdMap = productId2ZipAreaProductMap.get(productId);

            for (Map.Entry<Integer, AreaProductBoxRegulation> zip2AreaProductBoxRegulationMap : curAreaProductBoxRegulationMap.entrySet()) {
                AreaProductBoxRegulation areaProductBoxRegulation = zip2AreaProductBoxRegulationMap.getValue();

                Integer zip = areaProductBoxRegulation.getAreaZip();
                Integer boxRegulation = areaProductBoxRegulation.getBoxRegulation();

                String areaProductId = zip2AreaProductIdMap.get(zip);

                if (boxRegulation > 0 && areaProductId != null && !"".equals(areaProductId.trim())) {
                    if ("227fe88e-2877-48af-9ebd-d188654dd030".equals(productId)) {
                        System.out.println(boxRegulation);
                    }
                    String sql = createUpdateAreaProductBoxRegulationSql(areaProductId, boxRegulation);
                    if (updateCount % 50 == 0) {
                        writer.write(createSleepSql());
                    }
                    writer.write(sql);
                    updateCount++;
                }
            }
        }

        writer.close();
    }

    private static String createSleepSql() {
        return "SELECT SLEEP(1);\n";
    }

    /**
     * update `productareas` set `box_regulation` = where `id` = ''
     *
     * @param areaProductId
     * @param boxRegulation
     * @return
     */
    private static String createUpdateAreaProductBoxRegulationSql(String areaProductId, Integer boxRegulation) {
        return "update `productareas` set `box_regulation` = " + boxRegulation + " where `id` = '" + areaProductId + "';\n";
    }

//    /**
//     * 获取第一列的数据，以逗号分隔输出到文件。为查询数据做准备
//     *
//     * @param path
//     * @throws IOException
//     */
//    private static void getCell0FromExcelFile(String path) throws IOException {
//        Workbook workBook = createWorkBook(path);
//        Sheet sheetAt = workBook.getSheetAt(0);
//
//        FileWriter fw = new FileWriter("output.txt");
//        for (int i = 1; i < sheetAt.getPhysicalNumberOfRows(); i++) {
//            Row row = sheetAt.getRow(i);
//            Cell cell = row.getCell(0);
//            cell.setCellType(Cell.CELL_TYPE_STRING);
//            String code = cell.getStringCellValue().trim();
//            fw.write(code + ",");
//        }
//
//        fw.close();
//    }
}

