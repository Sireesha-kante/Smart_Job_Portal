package com.job.userservice.repository;

import com.job.userservice.entity.RecruiterDashboard;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterDashboardRepository extends JpaRepository<RecruiterDashboard, Long> {
    Optional<RecruiterDashboard> findByRecruiter_Id(Long recruiterId);
}
