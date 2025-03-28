package com.job.userservice.service;

import com.job.userservice.config.JWTUtility;

import com.job.userservice.dto.*;
import com.job.userservice.entity.*;
import com.job.userservice.exception.UserNotFoundException;
import com.job.userservice.repository.RecruiterDashboardRepository;
import com.job.userservice.repository.RecruiterProfileRepository;
import com.job.userservice.repository.UserRepository;


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
    public AuthResponse registerRecruiter(RegisterRecruiterRequest registerRequest) {

        User existingUser = userRepository.findByEmail(registerRequest.getEmail());

        if (existingUser != null) {
            throw new RuntimeException("Recruiter with this email already exists.");
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
        String jwtToken = jwtUtility.generateToken(recruiter);

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


        String jwtToken = jwtUtility.generateToken(existingRecruiter);
        System.out.println(jwtToken);

        return new AuthResponse(jwtToken,existingRecruiter.getRole());
    }

  
    @Override
    public RecruiterProfileDto getRecruiterProfile(Long userId) {
    	RecruiterProfile recruiterProfile=    recruiterProfileRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("Recruiter profile not found"));
    	
    	return new RecruiterProfileDto(
    			recruiterProfile.getId(),
    			recruiterProfile.getUser(),
    			recruiterProfile.getCompanyName(),
    			recruiterProfile.getCompanyWebsite(),
    			recruiterProfile.getIndustry(),
    			recruiterProfile.getLocation());
    }

   
    @Override
    public RecruiterProfile updateRecruiterProfile(UpdateRecruiterProfile updateRequest) {

    	RecruiterProfile recruiterProfile =  recruiterProfileRepository.findByUser(updateRequest.getUser())
            .orElseThrow(() -> new RuntimeException("Recruiter profile not found"));
        
        recruiterProfile.setCompanyName(updateRequest.getCompanyName());
        recruiterProfile.setCompanyWebsite(updateRequest.getCompanyWebsite());
        recruiterProfile.setIndustry(updateRequest.getIndustry());
        recruiterProfile.setLocation(updateRequest.getLocation());

    return recruiterProfileRepository.save(recruiterProfile);
    }

   
    @Override
    public RecruiterDashboardDto getRecruiterDashboard(Long recruiterId) {

        RecruiterDashboard dashboard = recruiterDashboardRepository.findByRecruiter_Id(recruiterId)
            .orElseThrow(() -> new RuntimeException("Recruiter dashboard not found"));

        return new RecruiterDashboardDto(
            dashboard.getTotalJobPostings(),
            dashboard.getTotalApplications(),
            dashboard.getScheduledInterviews()
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
	public String deleteUserDetails(AuthRequest authRequest) {
User user=userRepository.findByEmail(authRequest.getEmail());
		
		if(user ==null) {
			throw new UserNotFoundException("User not found");
		}
		recruiterProfileRepository.deleteById(user.getId());
		recruiterDashboardRepository.deleteById(user.getId());
		userRepository.deleteById(user.getId());
		
		return "User Deleted sucessfully";
	}

	}

