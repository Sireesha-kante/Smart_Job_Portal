package com.job.userservice.service;

import com.job.userservice.dto.UserDashboardDto;

public interface UserDashboardService {
	
	UserDashboardDto getUserDashboard(Long UserId);
	 void incrementJobApplications(Long userId);
	

}
