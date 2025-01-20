package com.brice.securityapp.config;

import com.brice.securityapp.exception.TokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtResetPasswordToken {

    public static final long JWT_TOKEN_VALIDITY = 60 * 60;

    @Value("${jwt.resetSecret}")
    private String secret;

    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateResetToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateResetToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // Utilise la clé pour valider le token
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject(); // Retourne le sujet (email) si le token est valide
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Le token est invalide. " + e);
        }
    }
}
