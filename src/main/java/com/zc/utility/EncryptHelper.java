/** 
* @title EncryptUtils.java
* @author xyzhuzhou/zhuzz@heptax.com
* @date：2016年7月15日 下午4:22:04 
* @Tag 加密帮助类 
* @Copyright 2016 知藏. All right reserved.
*/
package com.zc.utility;

import java.security.MessageDigest;

public class EncryptHelper {

    /**
     * 对字符串进行MD5加密
     * 
     * @param str
     *            需要加密的字符串
     * @return
     * @创建人 xyzhuzhou @创建时间 2016年7月15日 下午4:31:53
     */
    public static String encryptToMD5(String str) {

        byte[] digesta = null;

        try {

            MessageDigest alga = MessageDigest.getInstance("MD5");

            alga.update(str.getBytes());
            digesta = alga.digest();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String md5Str = byte2hex(digesta);

        return md5Str;

    }

    /**
     * 将二进制转化为16进制字符串
     * 
     * @param b
     * @return
     * @创建人 xyzhuzhou @创建时间 2016年7月15日 下午4:30:43
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();

    }

}
