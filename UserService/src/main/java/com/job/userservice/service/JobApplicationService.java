package com.job.userservice.service;

import java.util.List;

import com.job.userservice.entity.JobApplication;

public interface JobApplicationService {

	 public JobApplication applyForJob(Long userId, Long jobId);
	 
	  public List<JobApplication> getApplicationsByUserId(Long userId);
	  
	    public JobApplication updateApplicationStatus(Long applicationId, JobApplication.ApplicationStatus status);

}
