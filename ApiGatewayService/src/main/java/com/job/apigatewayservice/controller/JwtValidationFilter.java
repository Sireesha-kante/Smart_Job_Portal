package com.job.apigatewayservice.controller;

import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;

@Component
public class JwtValidationFilter extends AbstractGatewayFilterFactory<JwtValidationFilter.Config>{
	
	 public static class Config {
	        // No configuration needed
	    }
	
	private  SecretKey  secretKey;
	
	private byte[] key;
	
	
	 public JwtValidationFilter(@Value("${jwt.secret}") String secret) {
	     //   super(Config.class);
	        this.key = Base64.getDecoder().decode(secret);
	        this.secretKey =Keys.hmacShaKeyFor(key);
	    }
	 

	@Override
	public GatewayFilter apply(Config config) {
		// TODO Auto-generated method stub
	return (exchange, chain) ->{
		String token =exchange.getRequest().getHeaders().getFirst("Authorization");
		
		if (token == null || !token.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
		try {
			 String jwt = token.substring(7);
             Claims claims = Jwts.parser()
		             .verifyWith(secretKey)
		             .build()
		             .parseSignedClaims(token)
		             .getPayload();
			 List<String> roles = claims.get("roles", List.class); // For multiple roles
             
             System.out.println("Role in Token: " + roles);
			 
             return chain.filter(exchange);
			 
		}
		catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

	};

	}

	 
	 

}
