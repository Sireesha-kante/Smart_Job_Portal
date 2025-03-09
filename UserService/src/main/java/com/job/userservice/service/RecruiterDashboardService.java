package com.job.userservice.service;


import com.job.userservice.dto.RecruiterDashboardDto;

public interface RecruiterDashboardService {
	
	   RecruiterDashboardDto getRecruiterDashboard(Long recruiterId);
	    void incrementJobPostings(Long recruiterId);
	    void incrementTotalApplications(Long recruiterId);
	    void incrementScheduledInterviews(Long recruiterId);
	    //void handleJobPostedEvent(String recruiterId);


}
