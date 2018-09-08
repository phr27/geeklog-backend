package com.geeklog.common.util;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @author 潘浩然
 * 创建时间 2018/09/07
 * 功能：Jwt 工具类
 */
public class JwtUtil {

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：base64 需要的 key
     */
    private static final String JWT_SECRET = "b7e71a00824040449249faaf791e8b69";

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：生成签发 Jwt 需要的签名密钥
     */
    private SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}
