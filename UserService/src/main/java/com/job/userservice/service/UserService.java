package com.job.userservice.service;


import org.springframework.http.ResponseEntity;

import com.job.userservice.dto.AuthRequest;
import com.job.userservice.dto.AuthResponse;
import com.job.userservice.dto.RegisterJobSeekerRequest;
import com.job.userservice.dto.UpdateRequest;
import com.job.userservice.dto.UpdateUserProfile;
import com.job.userservice.dto.UserDashboardDto;
import com.job.userservice.dto.UserDto;
import com.job.userservice.dto.UserProfileDto;
import com.job.userservice.entity.User;
import com.job.userservice.entity.UserProfile;

public interface UserService {

    public AuthResponse registerUser(RegisterJobSeekerRequest registerRequest);

    public AuthResponse authenticate(AuthRequest authRequest);
    
    public UserDashboardDto getUserDashboard(Long userId);
    public UserProfile updateUserProfile(UpdateUserProfile updateRequest);
    public User updateUser(UpdateRequest updateRequest);
    public AuthResponse loginUser(AuthRequest authRequest);
    public UserProfileDto getUserProfile(Long userId);
    ResponseEntity<?> getUserDetails(String token);
    String deleteUserDetails(AuthRequest authRequest);


    
}
