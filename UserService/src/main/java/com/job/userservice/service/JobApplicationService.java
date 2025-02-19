package com.job.userservice.service;

import java.util.List;

import com.job.userservice.entity.JobApplication;
import com.job.userservice.entity.JobApplication.ApplicationStatus;
import com.job.userservice.entity.User;

public interface JobApplicationService {
	 	  
	    public JobApplication applyForJob(User user, Long jobId);

	public 	List<JobApplication> getApplicationsByUserId(User user);

	public JobApplication updateApplicationStatus(Long jobApplicationId, ApplicationStatus status, User recruiter);

}
