package com.job.userservice.controller;

import com.job.userservice.dto.AuthRequest;
import com.job.userservice.dto.RegisterRecruiterRequest;
import com.job.userservice.dto.UpdateRequest;
import com.job.userservice.exception.ResourceNotFoundException;
import com.job.userservice.exception.UserAlreadyExistsException;
import com.job.userservice.service.RecruiterService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/recruiter")
public class RecruiterController {

    private static final Logger logger = LoggerFactory.getLogger(RecruiterController.class);

    @Autowired
    private RecruiterService recruiterService;

    @PostMapping("/register")
    public ResponseEntity<?> registerRecruiter(@RequestBody RegisterRecruiterRequest registerRequest) {
        try {
            return ResponseEntity.ok(recruiterService.registerRecruiter(registerRequest));
        } catch (UserAlreadyExistsException ex) {
            logger.error("User already exists: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error during recruiter registration", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try again.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginRecruiter(@RequestBody AuthRequest authRequest) {
        try {
            return ResponseEntity.ok(recruiterService.loginRecruiter(authRequest));
        } catch (Exception ex) {
            logger.error("Login failed: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/profile/{userId}")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<?> getRecruiterProfile(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(recruiterService.getRecruiterProfile(userId));
        } catch (ResourceNotFoundException ex) {
            logger.error("Profile not found: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error fetching profile", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving profile");
        }
    }

    @PutMapping("/profile/update")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<?> updateRecruiterProfile(@RequestBody UpdateRequest updateRequest) {
        try {
            return ResponseEntity.ok(recruiterService.updateRecruiterProfile(updateRequest));
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
        return recruiterService.getUserDetails(extractedToken);
    }

}
