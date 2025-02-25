package com.job.applicationservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UserService", url = "http://localhost:8081")
public interface UserFeignClient {
    @GetMapping("/api/user/recruiter/{id}")
    RecruiterProfileDto getRecruiterProfile(@PathVariable Long id);
}