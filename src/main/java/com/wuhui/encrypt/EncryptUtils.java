package com.wuhui.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class EncryptUtils {
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final String MD5 = "MD5";
    private static final String AES = "AES";
    private static final String INSTANCE_STRING = "AES/ECB/PKCS5Padding";
    private static final String ENCODING_UTF8 = "UTF-8";

    public EncryptUtils() {
    }

    public static void main(String[] args) {
        String testStr = "store/product/123hrrqwdsdfgrefsdiufhsdifefnsdfnidungidffgrfasfsdewrwfdvfjgtergre";
        System.out.println(testStr.length());
        final String supplier = encryptByAES(testStr, "supplier");
        System.out.println(supplier);
        System.out.println(decryptByAES(supplier, "supplier"));
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);

        for(int j = 0; j < len; ++j) {
            buf.append(HEX_DIGITS[bytes[j] >> 4 & 15]);
            buf.append(HEX_DIGITS[bytes[j] & 15]);
        }

        return buf.toString();
    }

    public static String EncryptToSHA1(String str) {
        if (str == null) {
            return null;
        } else {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                messageDigest.update(str.getBytes());
                return getFormattedText(messageDigest.digest());
            } catch (Exception var2) {
                throw new RuntimeException(var2);
            }
        }
    }

    public static String saltEncode(String str) {
        String salt = "WX2A$9CV2q9K@2!y";
        String newStr = str.substring(1) + str.substring(0, 1);
        newStr = newStr + salt;
        return EncryptToSHA1(newStr);
    }

    public static SecretKeySpec setSecretKey(String secretKey) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] arr = md.digest(secretKey.getBytes());
        SecretKeySpec resultKey = new SecretKeySpec(arr, "AES");
        return resultKey;
    }

    public static String encryptByAES(String text, String secretKey) {
        try {
            SecretKeySpec md5key = setSecretKey(secretKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, md5key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes("UTF-8")));
        } catch (Exception var4) {
            return null;
        }
    }

    public static String decryptByAES(String text, String secretKey) {
        try {
            SecretKeySpec md5key = setSecretKey(secretKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, md5key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(text)));
        } catch (Exception var4) {
            return null;
        }
    }
}