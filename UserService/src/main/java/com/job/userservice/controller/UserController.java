package com.job.userservice.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.userservice.dto.AuthRequest;
import com.job.userservice.dto.RegisterJobSeekerRequest;
import com.job.userservice.dto.UpdateRequest;
import com.job.userservice.exception.ResourceNotFoundException;
import com.job.userservice.exception.UserAlreadyExistsException;
import com.job.userservice.service.UserService;


@RestController
@RequestMapping("/api/jobseeker")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(RecruiterController.class);
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerJobSeeker(@RequestBody RegisterJobSeekerRequest registerRequest){
    	try {
    		 System.out.println("Received Request: " + registerRequest.getEmail());
    		System.out.println("Received Password: " + registerRequest.getPassword());
    		return ResponseEntity.ok(userService.registerUser(registerRequest));
    	}
    	catch (UserAlreadyExistsException ex) {
			logger.error("User already exists: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		} 
    	catch (Exception ex) {
    		if (registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
    		    throw new IllegalArgumentException("Password cannot be null or empty");
    		}
            logger.error("Error during JobSeeker registration", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try again.");
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginJobSeeker(@RequestBody AuthRequest authRequest) {
        try {
            return ResponseEntity.ok(userService.loginUser(authRequest));
        } catch (Exception ex) {
            logger.error("Login failed: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    
    @GetMapping("/profile/{userId}")
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public ResponseEntity<?> getJobSeekerProfile(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(userService.getUserProfile(userId));
        } catch (ResourceNotFoundException ex) {
            logger.error("Profile not found: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error fetching profile", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving profile");
        }
    }
    
    @PutMapping("/profile/update")
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public ResponseEntity<?> updateJobSeekerProfile(@RequestBody UpdateRequest updateRequest) {
        try {
            return ResponseEntity.ok(userService.updateUserProfile(updateRequest));
        } catch (ResourceNotFoundException ex) {
            logger.error("Profile update failed: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error updating profile", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating profile");
        }
    }
    
    @GetMapping("/getUserDetails")
    public ResponseEntity<?> getUserDetails(@RequestHeader(value = "Authorization", required = false) String token) {
        System.out.println("Received Token: " + token); // Debugging

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header missing or invalid");
        }

        // Remove "Bearer " prefix
        String extractedToken = token.substring(7);
        return userService.getUserDetails(extractedToken);
    }
    
    
    
    
    
}
