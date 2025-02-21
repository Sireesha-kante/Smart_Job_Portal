package com.job.userservice.service;

import com.job.userservice.config.JWTUtility;
import com.job.userservice.dto.*;
import com.job.userservice.entity.*;
import com.job.userservice.repository.RecruiterDashboardRepository;
import com.job.userservice.repository.RecruiterProfileRepository;
import com.job.userservice.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RecruiterServiceImplementation implements RecruiterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    private RecruiterDashboardRepository recruiterDashboardRepository;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private PasswordEncoder passwordEncoder;
   
    @Override
    public AuthResponse registerRecruiter(RegisterRequest registerRequest) {

        User existingUser = userRepository.findByEmail(registerRequest.getEmail());

        if (existingUser != null) {
            throw new RuntimeException("User with this email already exists.");
        }

        User recruiter = new User(
            registerRequest.getUsername(),
            registerRequest.getEmail(),
            passwordEncoder.encode(registerRequest.getPassword()),
            registerRequest.getPhone(),
            registerRequest.getLocation(),
            registerRequest.getRole()
        );

        userRepository.save(recruiter);

        RecruiterProfile recruiterProfile = new RecruiterProfile();
        recruiterProfile.setUser(recruiter);
        recruiterProfile.setCompanyName(registerRequest.getCompanyName());
        recruiterProfile.setCompanyWebsite(registerRequest.getCompanyWebsite());
        recruiterProfile.setIndustry(registerRequest.getIndustry());
        recruiterProfile.setLocation(registerRequest.getLocation());
        recruiterProfileRepository.save(recruiterProfile);

        // Initialize Recruiter Dashboard
        RecruiterDashboard recruiterDashboard = new RecruiterDashboard();
        recruiterDashboard.setRecruiter(recruiter);
        recruiterDashboard.setTotalJobPostings(0);
        recruiterDashboard.setTotalApplications(0);
        recruiterDashboard.setScheduledInterviews(0);
        recruiterDashboardRepository.save(recruiterDashboard);

        // Generate JWT Token
        String jwtToken = jwtUtility.generateToken(recruiter.getEmail());

        return new AuthResponse(jwtToken, recruiter.getRole());
    }

    
    @Override
    public AuthResponse loginRecruiter(AuthRequest authRequest) {

        User existingRecruiter = userRepository.findByEmail(authRequest.getEmail());

        if (existingRecruiter == null) {
            throw new RuntimeException("User with this email does not exist.");
        }
        
//        if(!passwordEncoder.matches(authRequest.getPassword(), existingRecruiter.getPassword()))
//        		{
//                throw new RuntimeException("error credentials");
//            }


        String jwtToken = jwtUtility.generateToken(existingRecruiter.getEmail());
        System.out.println(jwtToken);

        return new AuthResponse(jwtToken,existingRecruiter.getRole());
    }

  
    @Override
    public RecruiterProfile getRecruiterProfile(Long userId) {
        return recruiterProfileRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("Recruiter profile not found"));
    }

   
    @Override
    public RecruiterProfile updateRecruiterProfile(UpdateRequest updateRequest) {

        RecruiterProfile recruiterProfile = recruiterProfileRepository.findByUserId(updateRequest.getUserId())
            .orElseThrow(() -> new RuntimeException("Recruiter profile not found"));

        recruiterProfile.setCompanyName(updateRequest.getCompanyName());
        recruiterProfile.setCompanyWebsite(updateRequest.getCompanyWebsite());
        recruiterProfile.setIndustry(updateRequest.getIndustry());
        recruiterProfile.setLocation(updateRequest.getLocation());

        return recruiterProfileRepository.save(recruiterProfile);
    }

   
    @Override
    public RecruiterDashboardDto getRecruiterDashboard(Long recruiterId) {

        RecruiterDashboard dashboard = recruiterDashboardRepository.findByRecruiterId(recruiterId)
            .orElseThrow(() -> new RuntimeException("Recruiter dashboard not found"));

        return new RecruiterDashboardDto(
            dashboard.getTotalJobPostings(),
            dashboard.getTotalApplications(),
            dashboard.getScheduledInterviews()
        );
    }
    
    @Override
    public ResponseEntity<?> getUserDetails(String receivedToken) {
        System.out.println("Received Token in Service: " + receivedToken);
        
        if (receivedToken == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Invalid or missing token");
        }

        // Extract username from JWT
        String username;
        try {
            username = jwtUtility.extractUsername(receivedToken);
            System.out.println("Extracted Username: " + username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Invalid token");
        }

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body("Token does not contain a valid username");
        }

        // Fetch user details from database
        User user = userRepository.findByEmail(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("User not found");
        }

        return ResponseEntity.ok(user);
    }

	}

