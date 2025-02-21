package com.job.userservice.repository;

import com.job.userservice.entity.RecruiterDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruiterDashboardRepository extends JpaRepository<RecruiterDashboard, Long> {
    Optional<RecruiterDashboard> findByRecruiterId(Long recruiterId);
}
