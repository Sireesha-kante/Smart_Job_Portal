package com.job.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job.userservice.entity.UserDashboard;

@Repository
public interface UserDashboardRepository extends JpaRepository<UserDashboard, Long> {
    UserDashboard findByUserId(Long userId);
	 void deleteById(Long userId);

}
