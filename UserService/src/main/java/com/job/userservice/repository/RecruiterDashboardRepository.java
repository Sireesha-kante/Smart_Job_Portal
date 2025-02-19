package com.job.userservice.repository;

import com.job.userservice.entity.RecruiterDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RecruiterDashboardRepository extends JpaRepository<RecruiterDashboard, Long> {
    Optional<RecruiterDashboard> findByRecruiterId(Long recruiterId);
}
