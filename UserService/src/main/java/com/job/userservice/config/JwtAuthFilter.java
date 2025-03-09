package com.job.userservice.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.stereotype.Component; 
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain; 
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest; 
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    
    private final JWTUtility jwtUtility;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public JwtAuthFilter(JWTUtility jwtUtility, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtility = jwtUtility;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        
        String token = extractToken(request);
        
        if (token != null && !jwtUtility.isTokenExpired(token) 
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            String username = jwtUtility.extractUsername(token);
            System.out.println("Received Token in Controller: " + token);
            System.out.println("Extracted Token: " + username);
            if (username != null) {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("User Authenticated: " + username);
            }
        }
        chain.doFilter(request, response);
    }
    
    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    
    
}
