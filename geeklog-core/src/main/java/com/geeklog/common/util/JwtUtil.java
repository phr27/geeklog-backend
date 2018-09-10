package com.geeklog.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.exception.SessionException;
import com.geeklog.domain.User;
import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @author 潘浩然
 * 创建时间 2018/09/07
 * 功能：Jwt 工具类
 * 修改时间 2018/09/09
 * 修改人：潘浩然
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
     * 创建时间 2018/09/09
     * 功能：Jwt 过期时间，单位毫秒，默认 7 天
     */
    private static final Long TTL_MILLIS = 604800000L;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：生成签发 Jwt 需要的签名密钥
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：根据用户创建 Jwt
     * @throws IllegalArgumentException owner 或 owner id 为 null
     */
    public static String createJwt(User owner) {
        Validator.notNull(owner, ValidatorException.unexpected("createJwt(owner cannot be null)"));
        Validator.notNull(owner.getUserId(), ValidatorException.unexpected("createJwt(owner id cannot be null)"));

        Date now = new Date();

        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", owner.getUserId());
        claims.put("username", owner.getUsername());
        claims.put("nickname", owner.getNickname());
        claims.put("avatar", owner.getAvatar());
        claims.put("is_admin", owner.getIsAdmin());

        return Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setSubject(owner.getUserId().toString())
                .signWith(SignatureAlgorithm.HS256, generalKey())
                .setExpiration(new Date(now.getTime() + TTL_MILLIS))
                .compact();
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：将 Jwt 解析为用户信息
     * @throws SessionException 解析过程中出现问题（过期，格式错误等）抛出该异常
     */
    public static User parseJwt(String jwt) {
        Validator.notBlank(jwt, ValidatorException.unexpected("parseJwt(jwt cannot be blank)"));

        try {
            Claims claims = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(jwt).getBody();

            User user = new User();
            user.setUserId(claims.get("user_id", Integer.class));
            user.setUsername(claims.get("username", String.class));
            user.setNickname(claims.get("nickname", String.class));
            user.setAvatar(claims.get("avatar", String.class));
            user.setIsAdmin(claims.get("is_admin", Boolean.class));

            return user;
        } catch (JwtException e) {
            throw SessionException.invalid(e);
        }
    }
}
