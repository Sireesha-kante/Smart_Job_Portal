package com.job.applicationservice.service;

import java.util.List;

import com.job.applicationservice.entity.JobApplication;
import com.job.applicationservice.entity.JobApplication.ApplicationStatus;

public class JobApplicationServiceImplementation implements JobApplicationService {

	@Override
	public JobApplication applyForJob(User user, Long jobId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobApplication> getApplicationsByUserId(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobApplication updateApplicationStatus(Long jobApplicationId, ApplicationStatus status, User recruiter) {
		// TODO Auto-generated method stub
		return null;
	}

}
