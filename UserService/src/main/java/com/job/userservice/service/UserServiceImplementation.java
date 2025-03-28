package com.job.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.job.userservice.config.JWTUtility;
import com.job.userservice.dto.*;
import com.job.userservice.entity.*;
import com.job.userservice.exception.*;
import com.job.userservice.repository.*;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImplementation implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserDashboardRepository userDashboardRepository;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AuthResponse registerUser(RegisterJobSeekerRequest registerRequest) {
        if (registerRequest == null || registerRequest.getEmail() == null || registerRequest.getPassword() == null) {
            throw new IllegalArgumentException("Email and Password are required.");
        }

        User existingUser = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with this email already exists.");
        }

        User user = new User(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                registerRequest.getPhone(),
                registerRequest.getLocation(),
                registerRequest.getRole()
        );
        userRepository.save(user);

        UserProfile userProfile = new UserProfile(
            user,
            registerRequest.getBio(),
            registerRequest.getLinkedinUrl(),
            registerRequest.getGithubUrl(),
            registerRequest.getResumeUrl(),
            registerRequest.getSkills(),
            registerRequest.getEducation(),
            registerRequest.getExperience(),
            registerRequest.getPreferredJobCategories()
        );
        userProfileRepository.save(userProfile);

        UserDashboard userDashboard = new UserDashboard(user, 0, 0, 0);
        userDashboardRepository.save(userDashboard);

        String jwtToken = jwtUtility.generateToken(user);
        return new AuthResponse(jwtToken, registerRequest.getRole());
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        User user = userRepository.findByEmail(authRequest.getEmail());
        if (user == null || !passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new UserNotFoundException("Invalid credentials");
        }
        String jwtToken = jwtUtility.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    @Override
    public UserDashboardDto getUserDashboard(Long userId) {
        UserDashboard userDashboard = userDashboardRepository.findByUserId(userId);
        if (userDashboard == null) {
            throw new UserNotFoundException("User dashboard not found");
        }
        return new UserDashboardDto(
            userDashboard.getTotalApplications(),
            userDashboard.getSavedJobs(),
            userDashboard.getUnreadNotifications()
        );
    }

    @Override
    @Transactional
    public UserProfile updateUserProfile(UpdateUserProfile updateRequest) {
        User user = userRepository.findById(updateRequest.getUser().getId())
        		       .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserProfile userProfile = userProfileRepository.findByUser(user);

        if (userProfile == null) {
            throw new UserNotFoundException("User profile not found");
        }

        userProfile.setBio(updateRequest.getBio());
        userProfile.setLinkedinUrl(updateRequest.getLinkedinUrl());
        userProfile.setGithubUrl(updateRequest.getGithubUrl());
        userProfile.setResumeUrl(updateRequest.getResumeUrl());
        userProfile.setSkills(updateRequest.getSkills());
        userProfile.setEducation(updateRequest.getEducation());
        userProfile.setExperience(updateRequest.getExperience());
        userProfile.setPreferredJobCategories(updateRequest.getPreferredJobCategories());

        return userProfileRepository.save(userProfile);
    }

    @Override
    @Transactional
    public User updateUser(UpdateRequest updateRequest) {
        User user = userRepository.findByEmail(updateRequest.getEmail());
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        user.setUsername(updateRequest.getUsername());
        user.setLocation(updateRequest.getLocation());

        // Update password only if a new one is provided
        if (updateRequest.getPassword() != null && !updateRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
        }

        user.setPhone(updateRequest.getPhone());
        user.setLocation(updateRequest.getLocation());

        return userRepository.save(user);
    }

    @Override
    public UserProfileDto getUserProfile(Long userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User profile not found"));
        
        return new UserProfileDto(
            userProfile.getId(),
            userProfile.getUser(),
            userProfile.getBio(),
            userProfile.getLinkedinUrl(),
            userProfile.getGithubUrl(),
            userProfile.getResumeUrl(),
            userProfile.getSkills(),
            userProfile.getEducation(),
            userProfile.getExperience(),
            userProfile.getPreferredJobCategories()
        );
    }

    @Override
    public ResponseEntity<?> getUserDetails(String receivedToken) {
        if (receivedToken == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or missing token");
        }

        String username;
        try {
            username = jwtUtility.extractUsername(receivedToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        User user = userRepository.findByEmail(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.ok(user);
    }

	@Override
	public AuthResponse loginUser(AuthRequest authRequest) {
	        
	        User existingUser = userRepository.findByEmail(authRequest.getEmail());
            System.out.println(existingUser);

	        if (existingUser == null) {
	            throw new RuntimeException("User with this email does not exist.");
	        }
            System.out.println(authRequest.getEmail());
	        
//	        if(!passwordEncoder.matches(authRequest.getPassword(), existingRecruiter.getPassword()))
//	        		{
//	                throw new RuntimeException("error credentials");
//	            }


	        String jwtToken = jwtUtility.generateToken(existingUser);
	        System.out.println(jwtToken);

	        return new AuthResponse(jwtToken,existingUser.getRole());
	}

	@Override
	@Transactional
	public String deleteUserDetails(AuthRequest authRequest) {
		User user=userRepository.findByEmail(authRequest.getEmail());
		
		if(user ==null) {
			throw new UserNotFoundException("User not found");
		}
		userProfileRepository.deleteById(user.getId());
		userDashboardRepository.deleteById(user.getId());
		userRepository.deleteById(user.getId());
		
		return "User Deleted sucessfully";
	}
	
	
	
}
