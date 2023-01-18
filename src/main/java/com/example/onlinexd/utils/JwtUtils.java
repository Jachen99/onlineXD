package com.example.onlinexd.utils;

import com.example.onlinexd.model.entity.User;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 *
 * jwt工具类
 *
 * @author JaChen
 * @date 2022/12/28 0:41
 */
public class JwtUtils {


    // 主题
    public static final String SUBJECT = "jachen";

    // 过期时间
    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;  //过期时间，毫秒，一周

    //秘钥
    public static final String APPSECRET = "jachen";

    /**
     * 生成jwt
     *
     * 根据用户信息生产令牌
     *
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user) {

        if (user == null || user.getId() == null || user.getHeadImg() == null) {
            return null;
        }
        return Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("img", user.getHeadImg())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact(); // 加密算法
    }


    /**
     * 校验token
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {

        try {
            final Claims body = Jwts.parser()
                    .setSigningKey(APPSECRET)
                    .parseClaimsJws(token)
                    .getBody();
            return body;
        } catch (Exception e) {
            return null;
        }
    }
}