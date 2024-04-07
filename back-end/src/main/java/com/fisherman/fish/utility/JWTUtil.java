package com.fisherman.fish.utility;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil {
    private SecretKey secretKey;
    public JWTUtil(@Value("${spring.jwt.secret}")String secret){
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public Object get(String token, String key){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get(key, String.class);
    }

    public String getUserId(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("id", String.class);
    }

    public String getRole(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(String id, String role, Long expiredMs){
        return Jwts.builder()
                .claim("id", id)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    public String createJwt(Map<String, Object> claims, Long expiredMs){
        System.out.print("JWTUtil - createJWT ");
        String ret = null;
        try {
            JwtBuilder jwtBuilder = Jwts.builder();
            claims.forEach((String key, Object val) -> {
                jwtBuilder.claim(key, val);
                System.out.print("claiming " + key + ": " + val);
            });
            ret = jwtBuilder
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + expiredMs))
                    .signWith(secretKey)
                    .compact();
            System.out.println("\n  -> " + ret + "created!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }
}
