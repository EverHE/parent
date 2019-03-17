package com.he.util;

import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {
    private static String CLAIM_KEY_USERNAME="username";

    @Value("${jwt.secret}")
    private static String secret;

    @Value("${jwt.expiration}")
    private static Integer expiration;

    @Value("${jwt.header}")
    public static String tokenHeader;

    @Value("${jwt.tokenHead}")
    public static String tokenHead;

    @Value("${jwt.route.authentication.auth}")
    public static String auth;

    @Value("${jwt.tokenHead}")
    public static String reflash;

    public static String register;

    /**
     * 根据token获取account
     * @param token
     * @return
     */
    public static String getAccountFromToken(String token) {
        String account;
        try {
            final Claims claims = getClaimsFromToken(token);
            account = claims.getSubject();
        } catch (Exception e) {
            account = null;
        }
        return account;
    }

    /**
     * 根据token获取token创建时间
     * @param token
     * @return
     */
    public static Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);

            created = DateUtil.offsetMillisecond(claims.getExpiration(),-expiration);
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 根据token获取token过期时间
     * @param token
     * @return
     */
    public static Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 根据token获取Claims
     * @param token
     * @return
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成过期时间
     * @return
     */
    public static Date generateExpirationDate() {
        return DateUtil.offsetMillisecond(DateUtil.date(),expiration);
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    public static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 判断token创建时间是否在用户密码更新之前
     * @param created
     * @param lastPasswordReset
     * @return
     */
    public static Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * 生成token
     * @param userDetails
     * @return
     */
    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        return generateToken(claims);
    }

    /**
     * 生成token
     * @param claims
     * @return
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证token
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        return false;
//        SysUser user = (SysUser) userDetails;
//        final String account = getAccountFromToken(token);
//        final Date created = getCreatedDateFromToken(token);
////        final Date expiration = getExpirationDateFromToken(token);
//        return (
//                account.equals(user.getAccount())
//                        && !isTokenExpired(token)
//                        && !isCreatedBeforeLastPasswordReset(created, user.getUpdateTime()));
    }
}
