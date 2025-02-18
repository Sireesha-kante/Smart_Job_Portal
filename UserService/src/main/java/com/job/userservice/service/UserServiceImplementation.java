package com.job.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.job.userservice.config.JWTUtility;
import com.job.userservice.dto.AuthRequest;
import com.job.userservice.dto.AuthResponse;
import com.job.userservice.dto.RegisterRequest;
import com.job.userservice.dto.UpdateRequest;
import com.job.userservice.dto.UserDashboardDto;
import com.job.userservice.entity.User;
import com.job.userservice.entity.UserDashboard;
import com.job.userservice.entity.UserProfile;
import com.job.userservice.repository.UserDashboardRepository;
import com.job.userservice.repository.UserProfileRepository;
import com.job.userservice.repository.UserRepository;

public class UserServiceImplementation implements UserService {

	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private UserProfileRepository userProfileRepository;
	    
	    @Autowired
	    private UserDashboardRepository userDashboardRepository;

	    @Autowired
	    private JWTUtility jwtUtility;
	    
	    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	@Override
	public AuthResponse register(RegisterRequest registerRequest) {
		
		User existingUser= userRepository.findByEmail(registerRequest.getEmail());
		
		if(existingUser!=null) {
	            throw new RuntimeException("User with this email already exists.");
	        }
		 
		 User user = new User(registerRequest.getUsername(), registerRequest.getEmail(),
                 passwordEncoder.encode(registerRequest.getPassword()),registerRequest.getRole());
        userRepository.save(user);
        UserProfile userProfile = new UserProfile(user.getId(), registerRequest.getEmail(), user, registerRequest.getBio(), 
                registerRequest.getLinkedinUrl(), registerRequest.getGithubUrl(),
                registerRequest.getEducation(), registerRequest.getExperience(), 
                registerRequest.getPreferredJobCategories());
			userProfileRepository.save(userProfile);
			UserDashboard userDashboard = new UserDashboard(user.getId(), user, 0, 0, 0); // Default values
			userDashboardRepository.save(userDashboard);

String jwtToken = jwtUtility.generateToken(user.getEmail());

return new AuthResponse(jwtToken, registerRequest.getRole());

	}

	@Override
	public AuthResponse authenticate(AuthRequest authRequest) {
		
		 User user = userRepository.findByEmail(authRequest.getEmail());
				 if (user == null) {
			            
					 throw new RuntimeException("Invalis Credentials");
			        }
	        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
	            throw new RuntimeException("Invalid credentials");
	        }

	        String jwtToken = jwtUtility.generateToken(user.getEmail());

	        return new AuthResponse(jwtToken);
		
	}

	@Override
	public UserDashboardDto getUserDashboard(Long userId) {
		
        UserDashboard userDashboard = userDashboardRepository.findByUserId(userId);

        if (userDashboard == null) {
            throw new RuntimeException("User dashboard not found");
        }
        return new UserDashboardDto(userDashboard.getTotalApplications(), 
                userDashboard.getSavedJobs(), 
                userDashboard.getUnreadNotifications());
		
	}

	@Override
	public UserProfile updateUserProfile(UpdateRequest updateRequest) {
		UserProfile userProfile = userProfileRepository.findByEmail(updateRequest.getEmail());
        if (userProfile == null) {
            throw new RuntimeException("User profile not found");
        }
 
        // Update profile fields
        userProfile.setBio(updateRequest.getBio());
        userProfile.setLinkedinUrl(updateRequest.getLinkedinUrl());
        userProfile.setGithubUrl(updateRequest.getGithubUrl());
        userProfile.setEducation(updateRequest.getEducation());
        userProfile.setExperience(updateRequest.getExperience());
        userProfile.setPreferredJobCategories(updateRequest.getPreferredJobCategories());
        
        userProfileRepository.save(userProfile);
        
        return userProfile;
	}

	@Override
	public User updateUser(UpdateRequest updateRequest) {
		User user = userRepository.findByEmail(updateRequest.getEmail());
		
		  if (user == null) {
	            throw new RuntimeException("User profile not found");
	        }
	 
		  user.setUsername(updateRequest.getUsername());
		  user.setEmail(updateRequest.getEmail());
		  user.setLocation(updateRequest.getLocation());
		  user.setPassword(updateRequest.getPassword());
		  user.setPhone(updateRequest.getPhone());
		  user.setResumeUrl(updateRequest.getResumeUrl());
		  user.setSkills(updateRequest.getSkills());
		  user.setRole(updateRequest.getRole());
		  
		  return user;
		  
	}

	@Override
	public AuthResponse login(AuthRequest authRequest) {
		   User user = userRepository.findByEmail(authRequest.getEmail());
	        if (user == null) {
	            throw new RuntimeException("Invalid credentials");
	        }

	        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
	            throw new RuntimeException("Invalid credentials");
	        }

	        // Generate JWT token for authenticated user
	        String jwtToken = jwtUtility.generateToken(user.getEmail());

	        return new AuthResponse(jwtToken, user.getRole());
	    
	}
	
	

}

