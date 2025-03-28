package com.job.apigatewayservice.controller;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class GatewayConfig {
	
	private final  JwtValidationFilter jwtValidationFilter;
	
	public GatewayConfig(JwtValidationFilter jwtValidationFilter ) {
		this.jwtValidationFilter=jwtValidationFilter;
	}
	
	@Bean
	public RouteLocator customRoutLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				 .route(p->p.path("/api/jobseeker/**","/api/recruiter/**")
						  .uri("lb://UserService"))
				 .route(p->p.path("/api/jobseeker//profile/{userId}","/api/recruiter//profile/{userId}")
						 .filters(f -> f.filter(jwtValidationFilter.apply(new JwtValidationFilter.Config())))
						  .uri("lb://UserService"))
				 
				 .route(p->p.path("/swagger-ui/**", "/v3/api-docs/**","/swagger-ui.html",
	                        "/webjars/**")
						  .uri("lb://USERSERVICE"))
				 .build();
					
	}

}