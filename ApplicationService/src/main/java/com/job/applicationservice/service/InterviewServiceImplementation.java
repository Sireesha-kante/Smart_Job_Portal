package com.job.applicationservice.service;

import java.time.LocalDateTime;
import java.util.List;

import com.job.applicationservice.entity.Interview;

public class InterviewServiceImplementation  implements InterviewService{

	@Override
	public Interview scheduleInterview(Long recruiterId, Long jobApplicationId, LocalDateTime interviewDate,
			String interviewLink) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interview> getScheduledInterviews(Long recruiterId) {
		// TODO Auto-generated method stub
		return null;
	}

}
