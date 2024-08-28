package com.fight_world.mono.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@PropertySource("classpath:application.yml")
public class JwtUtil {

    // Header Key 값
    public static final String AUTHORIZATION_HEADER = "Authorization";
    // Token 식별자
    public static final String BEARER_PREFIX = "Bearer ";
    // access token 1시간
    public static final long ACTIVE_TOKEN_TIME = 60 * 60 * 1000;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private SecretKey key;
    private final MacAlgorithm signatureAlgorithm = SIG.HS256;
    public static final Logger logger = LoggerFactory.getLogger("JWT 관련 로그");

    @PostConstruct
    public void init() {

        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // JWT 토큰을 substring
    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {

            return bearerToken.substring(7);
        }

        return null;
    }

    // JWT 검증
    public boolean validateToken(String token) {

        try {
            Jwts.parser().decryptWith(key).build().parseSignedClaims(token);

            return true;
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (JwtException e) {
            logger.error("Invalid JWT token, 유효하지 않는 JWT 서명 입니다.");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims is empty, 빈 JWT 토큰 입니다.");
        }

        return false;
    }

    // JWT에서 정보 가져오기
    public Claims getUserInfoFromToken(String token) {

        return Jwts.parser().decryptWith(key).build().parseSignedClaims(token).getPayload();
    }

    public String createAccessToken(String username) {

        Date date = new Date();

        return BEARER_PREFIX
                + Jwts.builder()
                .subject(username)
                .expiration(new Date(date.getTime() + ACTIVE_TOKEN_TIME))
                .issuedAt(date)
                .signWith(key, signatureAlgorithm)
                .compact();
    }
}
