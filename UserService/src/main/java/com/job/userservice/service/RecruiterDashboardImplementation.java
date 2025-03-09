package com.job.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.job.userservice.dto.RecruiterDashboardDto;
import com.job.userservice.entity.RecruiterDashboard;
import com.job.userservice.repository.RecruiterDashboardRepository;

@Service
public class RecruiterDashboardImplementation implements RecruiterDashboardService {
	
	@Autowired
    private RecruiterDashboardRepository recruiterDashboardRepository;
//	@Autowired
//	private UserRepository userRepository;

	@Override
	public RecruiterDashboardDto getRecruiterDashboard(Long recruiterId) {
		RecruiterDashboard recruiterDashboard=recruiterDashboardRepository.findByRecruiter_Id(recruiterId)
		   .orElseThrow(()-> new RuntimeException("Dashboard not found"));
		return  new RecruiterDashboardDto(
				recruiterDashboard.getTotalJobPostings(),
				recruiterDashboard.getTotalApplications(),
				recruiterDashboard.getScheduledInterviews()
	        );
	}
    
	@Override
	public void incrementJobPostings(Long recruiterId) {
		
		RecruiterDashboard recruiterDashboard=recruiterDashboardRepository.findByRecruiter_Id(recruiterId)
				   .orElseThrow(()-> new RuntimeException("Dashboard not found"));
		
		recruiterDashboard.setTotalJobPostings(recruiterDashboard.getTotalJobPostings()+1);
		recruiterDashboardRepository.save(recruiterDashboard);
		
	}

	@Override
	public void incrementTotalApplications(Long recruiterId) {
		RecruiterDashboard recruiterDashboard=recruiterDashboardRepository.findByRecruiter_Id(recruiterId)
				   .orElseThrow(()-> new RuntimeException("Dashboard not found"));
		
		recruiterDashboard.setTotalApplications(recruiterDashboard.getTotalApplications()+1);
		recruiterDashboardRepository.save(recruiterDashboard);
		
	}

	@Override
	public void incrementScheduledInterviews(Long recruiterId) {
		RecruiterDashboard recruiterDashboard=recruiterDashboardRepository.findByRecruiter_Id(recruiterId)
				   .orElseThrow(()-> new RuntimeException("Dashboard not found"));
		
		recruiterDashboard.setScheduledInterviews(recruiterDashboard.getScheduledInterviews()+1);
		recruiterDashboardRepository.save(recruiterDashboard);
		
	}

//	@Override
//	public void handleJobPostedEvent(@Payload String recruiterId) {
//		// TODO Auto-generated method stub
//		Long id=Long.parseLong(recruiterId);
//		incrementJobPostings(id);
//		
//	}
	//KafkaListener(topics="job-events", groupId="recruiter-dashboard-group")

}
