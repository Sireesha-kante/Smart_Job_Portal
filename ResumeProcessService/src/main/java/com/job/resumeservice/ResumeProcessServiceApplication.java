package com.job.resumeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ResumeProcessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeProcessServiceApplication.class, args);
	}

}
