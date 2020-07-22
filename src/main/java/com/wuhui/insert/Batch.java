package com.wuhui.insert;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Batch {

    public static void main(String[] args) throws Exception {
        FileOutputStream fis = new FileOutputStream(createSqlFile());

        FileChannel channel = fis.getChannel();

        // 文件信息
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        writeToChannel(buffer, channel, outFileInfo());
        // 为sql做准备
        buffer.clear();

        // 创建sql
        for (int i = SqlConstant.idPrefixStart; i <= SqlConstant.idPrefixEnd; i++) {
            String ltIdPrefix = "";
            String gtIdPrefix = "";

            if (i < SqlConstant.idPrefixEnd) {
                ltIdPrefix = toHex(i + 1, SqlConstant.idPrefixCount);
            }
            gtIdPrefix = toHex(i, SqlConstant.idPrefixCount);

            // System.out.println(createSql(ltIdPrefix, gtIdPrefix, UPDATE, true));
            // System.out.println(createSql(ltIdPrefix, gtIdPrefix, SELECT, true));
            // System.out.println(createSql(ltIdPrefix, gtIdPrefix, SqlConstant.INSERT, true));
            String sql = createSql(ltIdPrefix, gtIdPrefix, SqlConstant.INSERT, true);
            if (sql != null && !"".equals(sql.trim())) {
                writeToChannel(buffer, channel, sql);
                // 为下一次put做准备
                buffer.flip();
            }
        }

        channel.close();
        fis.close();
    }

    private static void writeToChannel(ByteBuffer buffer, FileChannel channel, String str) throws Exception {
        buffer.put(str.getBytes());
        buffer.flip();
        channel.write(buffer);
    }

    private static File createSqlFile() throws Exception {
        // 输出路径：classes/file_name.file_suffix
        String classpath = Batch.class.getClassLoader().getResource("").getPath();

        File file = new File(classpath + SqlConstant.SQL_FILE_NAME + SqlConstant.SQL_FILE_SUFFIX);
        if (file.exists()) {
            boolean isCreate = file.createNewFile();
            if (isCreate) {
                System.out.println("create file fail");
                return null;
            }
        }

        return file;
    }

    /**
     * 创建需要执行的sql语句
     * <p>
     *     UPDATE productauditex SET area_type = 1 WHERE id > '00' AND id < '01'
     * </p>
     *
     * @param ltIdPrefix id <
     * @param gtIdPrefix id >
     * @param usingWhere 是否带有where
     * @return sql
     */
    private static String createSql(String ltIdPrefix, String gtIdPrefix, String operateType, boolean usingWhere) {

        StringBuilder sb = new StringBuilder();

        // 分隔符，便于查看每条执行语句
        sb.append("###\n");

        // update, select, insert into
        if (SqlConstant.UPDATE.equals(operateType)) {
            sb.append(SqlConstant.UPDATE)
                    .append(" ")
                    .append(SqlConstant.TABLE_NAME)
                    .append(" SET")
                    // 需要更新的字段,
                    .append(" area_type = 1");
        }
        if (SqlConstant.SELECT.equals(operateType)) {
            sb.append(SqlConstant.SELECT)
                    .append(" *")
                    .append(" ")
                    .append(SqlConstant.FROM)
                    .append(" ")
                    .append(SqlConstant.TABLE_NAME);
        }
        if (SqlConstant.INSERT.equals(operateType)) {
            sb.append("insert into \n" +
                            "\t`productauditex`(id, product_id, change_data_type, user_id_create, user_name_create, user_id_update, user_name_update, time_create, time_update, time_pass_process) \n" +
                            "select \n" +
                            "\tUUID(), product_id, 8, user_id_create, user_name_create, user_id_update, user_name_update, time_create, time_create, 0 \n" +
                            "from \n" +
                            "\t`productauditex` \n" +
                            "where \n" +
                            "\t`change_data_type` = 4 and");
        }

        // where
        if (usingWhere) {
            if (!SqlConstant.INSERT.equals(operateType)) {
                sb.append(" ").append(SqlConstant.WHERE);
            }

            if (gtIdPrefix != null && !"".equals(gtIdPrefix.trim())) {
                sb.append(" id >").append(" '").append(gtIdPrefix).append("'");
            }
            if (gtIdPrefix != null && !"".equals(gtIdPrefix) && ltIdPrefix != null && !"".equals(ltIdPrefix.trim())) {
                sb.append(" ").append(SqlConstant.AND);
            }
            if (ltIdPrefix != null && !"".equals(ltIdPrefix.trim())) {
                sb.append(" id <").append(" '").append(ltIdPrefix).append("'");
            }
        }

        // ;
        sb.append(";");
        // 输出到文件，每一条sql需要进行换行
        sb.append("\n");

        return sb.toString();
    }

    private static String outFileInfo() {

        StringBuilder sb = new StringBuilder();

        sb.append("/*")
                .append("\n")
                .append("1.请求目的: ")
                .append(SqlConstant.REQUEST_PURPOSE)
                .append("\n")
                .append("2.请求执行数据库: ")
                .append(SqlConstant.REQUEST_OPT_DB)
                .append("\n")
                .append("3.请求执行时间: ")
                .append("yyyy-MM-DD HH:mm")
                .append("\n")
                .append("4.验证人员: ")
                .append("@author")
                .append("\n")
                .append("5.脚本名称: ")
                .append(SqlConstant.SQL_FILE_NAME + SqlConstant.SQL_FILE_SUFFIX)
                .append("\n")
                .append(" */")
                .append("\n\n");

        return sb.toString();
    }

    /**
     * 十进制转化成十六进制
     * 不使用Integer.toHexString的原因是：如果高位是0，需要对转化为String的结果进行补0
     *
     * @param idPrefix id前缀十六进制数
     * @param prefixCount 为idprefix 十六进制的个数
     * @return
     */
    private static String toHex(int idPrefix, int prefixCount) {
        StringBuilder sb = new StringBuilder();

        int num = idPrefix;

        while (--prefixCount >= 0) {
            int x = num & 0xF;
            sb.insert(0, SqlConstant.MAP[x]);
            num = (num >>> 4);
        }

        return sb.toString();
    }

    private static boolean compareHex(int num1, int num2) {
        return num1 > num2;
    }
}

class SqlConstant {

    public static char[] MAP = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static final String REQUEST_PURPOSE = "新增商品建档拓展订货合规审核完成记录";

    public static final String REQUEST_OPT_DB = "pupu_main";

    public static final String UPDATE = "UPDATE";

    public static final String UPDATE_FIELD = " area_type = 1";

    public static final String INSERT = "INSERT INTO";

    public static final String SELECT = "SELECT";

    public static final String FROM = "FROM";

    public static final String WHERE = "WHERE";

    public static final String AND = "AND";

    public static final String COMMA = ",";

    public static final String SQL_FILE_NAME = "wuhui-2020071010384-1";

    public static final String SQL_FILE_SUFFIX = ".sql";

    /**
     * 操作的表名
     */
    public static final String TABLE_NAME = "productauditex";

    public static final int BUFFER_CAPACITY = 1024;
    /**
     * 下面三个变量，修改其中某一个，其它两个都需要进行修改
     * 例如：
     * 0,f,1
     * 00,ff,2
     * 000,fff,3
     *
     * idPrefixCount的值越大，则创建的sql就越多，update的操作的锁粒度就越小
     */
    public static final int idPrefixStart = 0x00;

    public static final int idPrefixEnd = 0xff;

    public static final int idPrefixCount = 2;
}