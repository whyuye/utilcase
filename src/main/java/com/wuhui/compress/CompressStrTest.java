package com.wuhui.compress;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressStrTest {

    public static void main(String[] args) throws IOException {
        String str = "3c1ce823-b63f-4e2e-bec9-5ad69bf8b66a,b8d2045c-db70-481a-82d0-aeb5cafbba03," +
                "198c357f-4344-4780-87d0-5f42ab3d13d4,43400582-278a-4465-b6ed-dadcf14496cc," +
                "152dbda2-5322-4b0e-8c48-37ecc311c884,f3e3e12a-8f32-40e8-b6fd-2e105d4d5970," +
                "f06e4d7b-eadb-4ca9-99c8-827af21e9486,d623875a-e9cf-429d-9271-e59afefad086," +
                "f2b0ffb6-810d-4809-92ee-2433cb9c12af,e60e27b4-4c43-4aec-8058-6f8046f3a7e2," +
                "bb9e4624-caa1-4932-a6e7-33c745141b1e,72656f8f-6a88-4cf6-92e8-9846190d48ec," +
                "b6f7d92f-3326-4007-8d9f-cfc824564dd7,475195a6-78f8-4fef-9baa-2679b0cbee66," +
                "f41e77c9-3ad6-4d8f-87bc-293e8d88396d,299dd39b-1096-427d-865f-7041a4a728f5," +
                "4d5ab27e-af1a-4136-92a3-6ae4a69753cf,d11f82ef-af1f-45d1-80ae-fbe07ada3858," +
                "def4018a-fa1d-40fa-a914-61cb4d527c66,f452a29d-b228-4217-b834-43c5a7cbeef2," +
                "cec7d851-412a-4bd8-9e8f-4b8a800fce98,96dd6a2c-d5e6-4260-8191-5a29990870fc," +
                "5c3d0fc8-4792-451b-ae85-cbab15abc45b,949874fc-88e7-4cd0-b26f-7adb98c63fb4," +
                "3a3dc3a2-d88d-404c-908a-4202346cb089,2e0711db-7df2-47bc-a631-ff9d3db44a36," +
                "cef2e210-af02-4d8b-a59e-6cb5b06d5ac1,3e8e0912-33d3-437a-aab6-3ab43e216639," +
                "0ff48ae6-a826-481d-b2fe-9116e1b8a7ae,0e713483-6e3d-4c7f-9225-c930bc200322," +
                "6847263f-e6a5-4eca-ae42-1fe79c1abd8b,c030ad4e-3798-46d4-8f18-5729771bf18d," +
                "1c595cd0-f67e-410f-87d5-094b15329c88,f7d1776b-0607-4d45-a322-3a8aa13507df," +
                "8062ba83-494c-4ca5-aa72-f7ffe0c3be62,05869b50-8e27-466f-89f0-e8946926c737," +
                "7a244e2a-d6f0-48fe-ac1e-3cc7017c3cf3,d61c9538-0e50-4e5e-a7ca-d3cbf9b858a9," +
                "4f98a99b-488a-4847-9827-e412e1bd6a5c,bbe7e2ff-b830-4ba9-a35e-f95317579b9c," +
                "6277de68-c58c-4f44-8713-12566ec26d28,bbd0fb1d-0226-4519-ae71-95161c307327," +
                "b30788f5-c130-43e4-a0a6-0dfe532327f2,2f1a43b4-ff94-4d2c-b582-b758a1fdb6c7," +
                "014c081b-4e7f-4445-8003-123d74540063,e4e6e59f-28a4-466f-9599-f4b86984e81b";

        // 压缩
        String gzipStr = gzip(str);
        System.out.println("压缩后的字符串：" + gzipStr);
        System.out.println("压缩后的字符串的长度：" + gzipStr.length());

        // 解压缩
        String gunZipStr = gunzip(gzipStr);
        System.out.println("解压缩后的字符串：" + gunZipStr);
        System.out.println("解压缩后的字符串的长度：" + gunZipStr.length());
    }


    /**
     * 使用gzip进行压缩
     *
     * @param str 压缩前的文本
     * @return 返回压缩后的文本
     * @throws IOException 有异常时抛出，由调用者捕获处理
     */
    public static String gzip(String str) throws IOException {
        if (str == null || str.isEmpty()) {
            return str;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOut = new GZIPOutputStream(out)) {
            gzipOut.write(str.getBytes());
        }

        return Base64.encodeBase64String(out.toByteArray());
    }

    /**
     * 使用gzip进行解压缩
     *
     * @param compressedStr 压缩字符串
     * @return 解压字符串
     * @throws IOException 有异常时抛出，由调用者捕获处理
     */
    public static String gunzip(String compressedStr) throws IOException {
        if (compressedStr == null || compressedStr.isEmpty()) {
            return compressedStr;
        }

        byte[] compressed = Base64.decodeBase64(compressedStr);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(compressed);

        try (GZIPInputStream gzipIn = new GZIPInputStream(in)) {
            byte[] buffer = new byte[4096];
            int len = -1;
            while ((len = gzipIn.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        }

        return out.toString();
    }
}
