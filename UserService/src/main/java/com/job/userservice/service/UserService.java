package com.job.userservice.service;

import java.util.List;

import com.job.userservice.dto.AuthRequest;
import com.job.userservice.dto.AuthResponse;
import com.job.userservice.dto.RegisterRequest;
import com.job.userservice.dto.UpdateRequest;
import com.job.userservice.dto.UserDashboardDto;
import com.job.userservice.entity.User;
import com.job.userservice.entity.UserProfile;

public interface UserService {

    public AuthResponse register(RegisterRequest registerRequest);

    public AuthResponse authenticate(AuthRequest authRequest);
    
    public UserDashboardDto getUserDashboard(Long userId);
    public UserProfile updateUserProfile(UpdateRequest updateRequest);
    public User updateUser(UpdateRequest updateRequest);
    public AuthResponse login(AuthRequest authRequest);
    
}
