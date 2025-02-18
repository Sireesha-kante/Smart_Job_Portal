package com.job.userservice.repository;

import com.job.userservice.entity.UserDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDashboardRepository extends JpaRepository<UserDashboard, Long> {
    UserDashboard findByUserId(Long userId);
}
