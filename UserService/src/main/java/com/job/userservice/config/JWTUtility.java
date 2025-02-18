package com.job.userservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Component
public class JWTUtility {

    private final String SECRET_KEY = "mysecretkeymysecretkeymysecretkeymysecretkey"; // Ensure the key is at least 256 bits (32 bytes)

    // Generate a secure key from the secret key string
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email) // Use subject() instead of setSubject()
                .issuedAt(new Date()) // Use issuedAt() instead of setIssuedAt()
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
                .signWith(getSigningKey()) // Use signWith() with Key and SignatureAlgorithm
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(getSigningKey()) // Use verifyWith() instead of setSigningKey()
                .build()
                .parseSignedClaims(token) // Use parseSignedClaims() instead of parseClaimsJws()
                .getPayload(); // Use getPayload() instead of getBody()
        return claimsResolver.apply(claims);
    }
}