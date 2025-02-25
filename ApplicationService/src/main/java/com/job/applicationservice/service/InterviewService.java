package com.job.applicationservice.service;

import java.time.LocalDateTime;
import java.util.List;

import com.job.applicationservice.entity.Interview;


public interface InterviewService {
	
	 public Interview scheduleInterview(Long recruiterId, Long jobApplicationId, LocalDateTime interviewDate, String interviewLink);
	 
	  public List<Interview> getScheduledInterviews(Long recruiterId);
}

