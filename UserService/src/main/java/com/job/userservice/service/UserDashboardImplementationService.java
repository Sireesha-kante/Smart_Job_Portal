package com.job.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.job.userservice.dto.UserDashboardDto;
import com.job.userservice.entity.UserDashboard;
import com.job.userservice.repository.UserDashboardRepository;

public class UserDashboardImplementationService implements UserDashboardService {
	
	@Autowired
	private UserDashboardRepository userDashboardRepository;

	@Override
	public UserDashboardDto getUserDashboard(Long UserId) {
		UserDashboard userDashboard =userDashboardRepository.findByUserId(UserId);
		if(userDashboard==null) {
			throw new RuntimeException("User dashboard missing retry!!!");
		}
		return new UserDashboardDto(
				userDashboard.getTotalApplications(),
				userDashboard.getSavedJobs(),
				userDashboard.getUnreadNotifications()
				);
	}

	@Override
	public void incrementJobApplications(Long userId) {
		
		UserDashboard userDashboard=userDashboardRepository.findByUserId(userId);
		
		if(userDashboard==null) {
			throw new RuntimeException("User dashboard missing retry!!!");
		}
		userDashboard.setTotalApplications(userDashboard.getTotalApplications()+1);
		userDashboardRepository.save(userDashboard);
		
	}

}
