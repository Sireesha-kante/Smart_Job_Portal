package com.job.userservice.service;

import org.springframework.http.ResponseEntity;

import com.job.userservice.dto.*;
import com.job.userservice.entity.RecruiterProfile;
import com.job.userservice.entity.User;

import jakarta.servlet.http.HttpServletRequest;

public interface RecruiterService {
    AuthResponse registerRecruiter(RegisterRequest registerRequest);
    AuthResponse loginRecruiter(AuthRequest authRequest);
    RecruiterProfile getRecruiterProfile(Long userId);
    RecruiterProfile updateRecruiterProfile(UpdateRequest updateRequest);
    RecruiterDashboardDto getRecruiterDashboard(Long recruiterId);
	ResponseEntity<?> getUserDetails(String token);
}
