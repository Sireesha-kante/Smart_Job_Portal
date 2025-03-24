package com.job.securityconfigservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/config")
@RefreshScope
public class SecurityConfigController {
	
//	private final Map<String, String> configStore = new HashMap<>();
//	
//	@PostMapping("/jwt-secret")
//    public String updateSecretKey(@RequestBody Map<String, String> secretData) {
//        configStore.put("jwt.secret", secretData.get("jwt.secret"));
//        return "JWT Secret Key updated successfully.";
//    }
//
//    @GetMapping("/jwt-secret")
//    public String getSecretKey() {
//        return configStore.getOrDefault("jwt.secret", "No key available");
//    }
	
	   @GetMapping("/")
	    public String welcome() {
	        return "Welcome to the Spring Cloud Config Server!";
	    }
	

}
