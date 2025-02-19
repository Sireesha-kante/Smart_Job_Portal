package com.job.userservice.service;

import com.job.userservice.dto.*;
import com.job.userservice.entity.RecruiterProfile;

public interface RecruiterService {
    AuthResponse registerRecruiter(RegisterRequest registerRequest);
    AuthResponse loginRecruiter(AuthRequest authRequest);
    RecruiterProfile getRecruiterProfile(Long userId);
    RecruiterProfile updateRecruiterProfile(UpdateRequest updateRequest);
    RecruiterDashboardDto getRecruiterDashboard(Long recruiterId);
}
