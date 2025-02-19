package com.job.userservice.service;

import com.job.userservice.config.JWTUtility;
import com.job.userservice.dto.*;
import com.job.userservice.entity.*;
import com.job.userservice.repository.RecruiterDashboardRepository;
import com.job.userservice.repository.RecruiterProfileRepository;
import com.job.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ✅ Recruiter Registration
    @Override
    public AuthResponse registerRecruiter(RegisterRequest registerRequest) {

		User existingUser= userRepository.findByEmail(registerRequest.getEmail());
		
		if(existingUser!=null) {
	            throw new RuntimeException("User with this email already exists.");
	        }
        // Create new recruiter user
        User recruiter = new User(registerRequest.getUsername(), registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()), registerRequest.getPhone(),
                registerRequest.getLocation(), User.Role.RECRUITER);
        userRepository.save(recruiter);

        // Create Recruiter Profile
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

        return new AuthResponse(jwtToken, User.Role.RECRUITER);
    }

    // ✅ Recruiter Login
    @Override
    public AuthResponse loginRecruiter(AuthRequest authRequest) {

		User existingRecruiter= userRepository.findByEmail(authRequest.getEmail());
		
		if(existingRecruiter!=null) {
	            throw new RuntimeException("User with this email already exists.");
	        }
        if (!existingRecruiter.getRole().equals(User.Role.RECRUITER)) {
            throw new RuntimeException("User is not a recruiter.");
        }

        if (!passwordEncoder.matches(authRequest.getPassword(), existingRecruiter.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String jwtToken = jwtUtility.generateToken(existingRecruiter.getEmail());
        return new AuthResponse(jwtToken, User.Role.RECRUITER);
    }

    // ✅ Get Recruiter Profile
    @Override
    public RecruiterProfile getRecruiterProfile(Long userId) {
        return recruiterProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Recruiter profile not found"));
    }

    // ✅ Update Recruiter Profile
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

    // ✅ Get Recruiter Dashboard
    @Override
    public RecruiterDashboardDto getRecruiterDashboard(Long recruiterId) {
        RecruiterDashboard dashboard = recruiterDashboardRepository.findByRecruiterId(recruiterId)
                .orElseThrow(() -> new RuntimeException("Recruiter dashboard not found"));

        return new RecruiterDashboardDto(dashboard.getTotalJobPostings(), 
                dashboard.getTotalApplications(), 
                dashboard.getScheduledInterviews());
    }
}
