package com.gkh.cipher;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 依赖于org.apache.commons.codec.digest.DigestUtils,支持MD2,MD5,SHA-1,SHA-256,SHA-384,SHA-512
 * @author gekaihui
 * @date 2020/5/20
 */
public class CipherUtil {
    public static final String MD2 = "MD2";
    public static final String MD5 = "MD5";
    public static final String SHA_1 = "SHA1";
    public static final String SHA_256 = "SHA256";
    public static final String SHA_384 = "SHA384";
    public static final String SHA_512 = "SHA512";

    /**
     * 加密字符串
     * 
     * @Author: gekaihui
     * @Date: 2020/5/20 15:03
     */
     public static String encryString(String sourceStr,String encryName){
            String password = "";
            switch(encryName){
                case "MD2":
                         password = DigestUtils.md2Hex(sourceStr);
                         break;
                 case "MD5":
                         password = DigestUtils.md5Hex(sourceStr);
                        break;
                 case "SHA1":
                         password = DigestUtils.sha1Hex(sourceStr);
                         break;
                 case "SHA256":
                         password = DigestUtils.sha256Hex(sourceStr);
                        break;
                case "SHA384":
                         password = DigestUtils.sha384Hex(sourceStr);
                         break;
                 case "SHA512":
                        password = DigestUtils.sha512Hex(sourceStr);
                        break;
                 }
             return password;
    }
}
