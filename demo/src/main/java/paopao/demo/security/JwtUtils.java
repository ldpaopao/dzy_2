package paopao.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    // 密钥与测试类中一致
    private static final String SECRET_KEY = "3AvV5Kxw92f4FgR87Qb0sPvE6FLUUN9TqkzZJWdGtLmNjXo=";
    // 12小时的毫秒数（单位：ms）
    private static final long EXPIRATION = 12 * 60 * 60 * 1000;

    /**
     * 生成 JWT 令牌
     *
     * @param claims 自定义负载数据（如用户信息）
     * @return 返回生成的 JWT 令牌
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 解析 JWT 令牌
     *
     * @param token 要解析的令牌字符串
     * @return 返回解析后的 Claims，包含自定义负载数据
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}