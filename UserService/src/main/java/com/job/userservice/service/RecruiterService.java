package com.job.userservice.service;

import org.springframework.http.ResponseEntity;


import com.job.userservice.dto.*;
import com.job.userservice.entity.RecruiterProfile;

public interface RecruiterService {
	
    AuthResponse registerRecruiter(RegisterRecruiterRequest registerRequest);
    AuthResponse loginRecruiter(AuthRequest authRequest);
    RecruiterProfileDto getRecruiterProfile(Long userId);
    RecruiterProfile updateRecruiterProfile(UpdateRequest updateRequest);
    RecruiterDashboardDto getRecruiterDashboard(Long recruiterId);
	ResponseEntity<?> getUserDetails(String token);
}
