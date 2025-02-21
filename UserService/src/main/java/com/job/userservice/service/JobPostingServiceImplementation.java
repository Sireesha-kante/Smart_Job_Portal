/*
 * package com.job.userservice.service;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * import com.job.userservice.entity.JobPosting; import
 * com.job.userservice.entity.RecruiterProfile; import
 * com.job.userservice.repository.JobPostingRepository; import
 * com.job.userservice.repository.RecruiterProfileRepository;
 * 
 * public class JobPostingServiceImplementation implements JobPostingService {
 * 
 * @Autowired private JobPostingRepository jobPostingRepository;
 * 
 * @Autowired private RecruiterProfileRepository recruiterProfileRepository;
 * 
 * @Override public JobPosting createJob(Long recruiterId, JobPosting
 * jobPosting) { RecruiterProfile recruiter =
 * recruiterProfileRepository.findById(recruiterId) .orElseThrow(() -> new
 * RuntimeException("Recruiter not found"));
 * 
 * jobPosting.setRecruiter(recruiter); return
 * jobPostingRepository.save(jobPosting); }
 * 
 * @Override public List<JobPosting> getJobsByRecruiter(Long recruiterId) {
 * return jobPostingRepository.findByRecruiterId(recruiterId); }
 * 
 * @Override public void deleteJob(Long jobId) {
 * jobPostingRepository.deleteById(jobId); }
 * 
 * 
 * }
 */