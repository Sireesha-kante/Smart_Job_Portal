package com.job.userservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import com.job.userservice.entity.User;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class JWTUtility {
	  
	 @Value("${jwt.secret}") // This should be a base64-encoded string
	    private String jwtSecretString;
	    
	    private SecretKey signingKey;
	    
	    @PostConstruct
	    public void init() {
	        // Convert the configured string to a SecretKey once at startup
	        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretString);
	        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
	    }

    public String generateToken(User user) {
    	   System.out.println(signingKey);
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("roles", List.of(user.getRole().name()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1-hour expiry
                .signWith(signingKey, Jwts.SIG.HS256)
                .compact();
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String extractUsername(String token) {
    	System.out.println("Received Token in Controller: " + token);
     
        return isTokenExpired(token) ? null : extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    public List<String> extractRoles(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.get("roles", List.class);
    }
}
