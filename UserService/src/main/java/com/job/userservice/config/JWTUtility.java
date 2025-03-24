package com.job.userservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtility {
	
	
//	  @Value("${config.server.url}")
//	    private String configServerUrl;
//	  
//	  private final RestTemplate restTemplate = new RestTemplate();
//	  
//	  
//	  public JWTUtility() {
//	        pushSigningKeyToConfigServer();
//	    }
//
//	    private void pushSigningKeyToConfigServer() {
//	        try {
//	        	 System.out.println("Config Server URL: " + configServerUrl); 
//	            Map<String, String> configData = new HashMap<>();
//	            configData.put("jwt.secret", base64Key);
//	            restTemplate.postForObject(configServerUrl + "/update-key", configData, String.class);
//	            System.out.println("JWT Secret Key stored in Config Server.");
//	        } catch (Exception e) {
//	            System.err.println("Error pushing key to Config Server: " + e.getMessage());
//	        }
//
//	    }

    // Static Secure Key Initialization
	static byte[] keyBytes = new byte[64]; // 512 bits for HS384
	static String base64Key = Base64.getEncoder().encodeToString(keyBytes);
	private static final SecretKey SIGNING_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Key));

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1-hour expiry
                .signWith(SIGNING_KEY, Jwts.SIG.HS384)
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
                .verifyWith(SIGNING_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }
}
