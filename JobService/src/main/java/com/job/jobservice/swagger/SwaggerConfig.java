package com.job.jobservice.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
public class SwaggerConfig {
	/*
     * Configures the OpenAPI documentation for the User Service.
     * - Sets API title, description, and version.
     * - Adds Bearer Token (JWT) authentication support.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Job Portal Job Service APIS")
                .version("1.0")
                .description("This API manages Job posting, Job Applyocation.Use the endpoints to post a job , update, delete a job by recruiter and applys for a job my jobseekers in SmartJobPortal.")
                .contact(new Contact()
                		.name("Smart JobPortal Support Team")
                		.email("jobportal@gmail.com")
                		.url("www.smartjobportal.com")
                		))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                		.addSecuritySchemes("BearerAuth", new SecurityScheme()
                											.name("BearerAuth")
                											.type(SecurityScheme.Type.HTTP)
                											.scheme("bearer")
                											.bearerFormat("JWT")));
    }
}
