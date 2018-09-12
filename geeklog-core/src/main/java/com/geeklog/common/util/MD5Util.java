package com.geeklog.common.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * @author 潘浩然
 * 创建时间 2018/09/10
 * 功能：md5 随机盐加密
 */
public class MD5Util {

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：随机盐长度
     */
    private static final int SALT_LENGTH = 6;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：md5 随机盐加密
     * @param plainText 要加密的明文
     */
    public static String md5(String plainText) {
        Validator.password(plainText, "MD5Util.md5(plainText 不符合密码格式)");

        String salt = RandomStringUtils.random(SALT_LENGTH, "0123456789abcdef");
        String encoded = DigestUtils.md5DigestAsHex((plainText + salt).getBytes());

        return encoded + salt;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：校验明文
     */
    public static boolean verify(String plainText, String encoded) {
        Validator.password(plainText, "MD5Util.verify(plainText 不符合密码格式)");

        if (StringUtils.isBlank(encoded) || encoded.length() != 32 + SALT_LENGTH) {
            return false;
        }

        String salt = encoded.substring(32);
        return StringUtils.equals(DigestUtils.md5DigestAsHex((plainText + salt).getBytes()), encoded.substring(0, 32));
    }
}
