package com.casaba.util;

import java.security.MessageDigest;

/**
 * created by Ulric on 2018/7/27
 */

public final class CommonUtil {

    /**
     * 将字符串进行 sha1 加密
     *
     * @author Ulric
     * @date 2018/7/17
     */
    public static String encryptInSha1(String str) {
        if (str == null || str.length() == 0) { return null; }

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 生成指定长度的随机正整数
     *
     * @param length 生成的随机数的长度
     * @author Ulric
     * @date 2018/7/27
     */
    public static int genRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }
}
