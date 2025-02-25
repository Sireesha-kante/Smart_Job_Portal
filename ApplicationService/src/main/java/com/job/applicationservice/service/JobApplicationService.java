package com.job.applicationservice.service;

import java.util.List;

import com.job.applicationservice.entity.JobApplication;
import com.job.applicationservice.entity.JobApplication.ApplicationStatus;
 interface JobApplicationService {
	  
    public JobApplication applyForJob(User user, Long jobId);

public 	List<JobApplication> getApplicationsByUserId(User user);

public JobApplication updateApplicationStatus(Long jobApplicationId, ApplicationStatus status, User recruiter);

}
