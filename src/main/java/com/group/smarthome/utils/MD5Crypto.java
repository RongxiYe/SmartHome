package com.group.smarthome.utils;
import java.security.MessageDigest;

/**
 * received from another group member
 */
public class MD5Crypto {
    private static final String slat = "&%5123***&&%%$$#@";

    public static String encrypt(String dataStr) {
        try {
            dataStr = dataStr + slat;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

//    public static void main(String[] args) {
//        String str = " O0O2SdHhzKt";
//        System.out.println(MD5Crypto.encrypt(str));
//    }
}
