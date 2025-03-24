package com.job.jobservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.job.jobservice.dto.JobApplicationDto;
import com.job.jobservice.dto.JobApplicationMapper;
import com.job.jobservice.dto.JobPostingDto;
import com.job.jobservice.dto.JobPostingMapper;
import com.job.jobservice.entity.JobApplication;
import com.job.jobservice.entity.JobPosting;
import com.job.jobservice.exception.JobNotFoundException;
import com.job.jobservice.repository.JobApplicationRepository;
import com.job.jobservice.repository.JobPostingRepository;

public class JobApplicationServiceImplementation implements JobApplicationService {

	
	   @Autowired
	   private  JobPostingRepository jobPostingRepository;
	   
	   @Autowired
	   private JobApplicationRepository jobApplicationRepository;
	   
	   @Autowired
	   private JobApplicationMapper jobApplicationMapper;
	   
	   @Autowired
	   private JobPostingMapper jobPostingMapper;

	@Override
	public JobApplicationDto applyForJob(Long userId, Long jobId) {
		
		jobApplicationRepository.findById(jobId)
		          						.orElseThrow(() -> new JobNotFoundException("Job not found"));
		
		  if (jobApplicationRepository.findByUserIdAndJobId(userId, jobId).isPresent()) {
		      throw new RuntimeException("User has already applied for this job");
		  }
		
		    JobApplication jobApplication = jobApplicationMapper.toEntity(userId, jobId);
		    
		    JobApplication appliedJob = jobApplicationRepository.save(jobApplication);
		return jobApplicationMapper.toDTO(appliedJob);
				
	}
	

	@Override
	public List<JobPostingDto> getAppliedJobs(Long userId) {
		  // Fetch all job applications for the given user
	    List<JobApplication> appliedJobs = jobApplicationRepository.findByUserId(userId);
	    
	    // Check if the user has applied for any jobs
	    if (appliedJobs.isEmpty()) {
	        throw new RuntimeException("No applied jobs found for this user");
	    }

	    // Extract job IDs from applied job list
	    List<Long> jobIds = appliedJobs.stream()
	            .map(JobApplication::getJobId)
	            .collect(Collectors.toList());

	    // Fetch all job postings for the applied job IDs in a single query
	    List<JobPosting> jobPostings = jobPostingRepository.findAllById(jobIds);

	    // Convert job entities to DTOs
	    return jobPostings.stream()
	            .map(jobPostingMapper::toDTO)
	            .collect(Collectors.toList());
	}


}
