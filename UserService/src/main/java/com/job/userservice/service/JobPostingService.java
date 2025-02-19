package com.job.userservice.service;

import java.util.List;

import com.job.userservice.entity.JobPosting;

public interface JobPostingService {
	
	public JobPosting createJob(Long recruiterId, JobPosting jobPosting);
	  public List<JobPosting> getJobsByRecruiter(Long recruiterId);
	  public void deleteJob(Long jobId);
}
