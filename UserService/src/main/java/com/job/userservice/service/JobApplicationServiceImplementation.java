/*
 * package com.job.userservice.service;
 * 
 * import java.time.LocalDateTime; import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.job.userservice.config.JWTUtility; import
 * com.job.userservice.entity.JobApplication; import
 * com.job.userservice.entity.User; import
 * com.job.userservice.exception.ResourceNotFoundException; import
 * com.job.userservice.exception.UnauthorizedException; import
 * com.job.userservice.repository.JobApplicationRepository; import
 * com.job.userservice.repository.SavedJobRepository; import
 * com.job.userservice.repository.UserRepository;
 * 
 * import jakarta.transaction.Transactional;
 * 
 * @Service public class JobApplicationServiceImplementation implements
 * JobApplicationService {
 * 
 * @Autowired private JobApplicationRepository jobApplicationRepository;
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * @Autowired private SavedJobRepository jobRepository;
 * 
 * @Autowired private JWTUtility jwtUtility;
 * 
 * @Override
 * 
 * @Transactional public JobApplication applyForJob(User users, Long jobId) {
 * String email = jwtUtility.generateToken(users.getEmail()); // Get email from
 * JWT User user = userRepository.findByEmail(email); if (user == null ||
 * !user.getId().equals(users.getId())) { throw new
 * IllegalStateException("Unauthorized: User not logged in."); }
 * 
 * // Check if user has already applied if
 * (jobApplicationRepository.existsByUserIdAndJobId(user.getId(), jobId)) {
 * throw new IllegalStateException("User has already applied for this job."); }
 * 
 * // Save job application JobApplication jobApplication = new
 * JobApplication(user,JobApplication.ApplicationStatus.PENDING,LocalDateTime.
 * now()); return jobApplicationRepository.save(jobApplication); }
 * 
 * @Override public List<JobApplication> getApplicationsByUserId(User user) { if
 * (!userRepository.existsById(user.getId())) { throw new
 * ResourceNotFoundException("No Applications found with email: "
 * +user.getEmail()); } return
 * jobApplicationRepository.findByUserId(user.getId()); }
 * 
 * @Override
 * 
 * @Transactional public JobApplication updateApplicationStatus(Long
 * jobApplicationId, JobApplication.ApplicationStatus status, User recruiter) {
 * JobApplication jobApplication =
 * jobApplicationRepository.findById(jobApplicationId) .orElseThrow(() -> new
 * ResourceNotFoundException("Application not found with ID: " +
 * jobApplicationId));
 * 
 * // Ensure only the recruiter can update the status if
 * (!recruiter.getRole().equals(User.Role.RECRUITER)) { throw new
 * UnauthorizedException("Only recruiters can update application status."); }
 * 
 * jobApplication.setStatus(status); return
 * jobApplicationRepository.save(jobApplication); }
 * 
 * 
 * }
 */