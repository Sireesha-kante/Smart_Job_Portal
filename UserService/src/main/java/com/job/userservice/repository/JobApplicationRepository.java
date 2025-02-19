package com.job.userservice.repository;

import com.job.userservice.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUserId(Long userId);
    List<JobApplication> findByUserIdAndStatus(Long userId, JobApplication.ApplicationStatus status);
	boolean existsByUserIdAndJobId(Long id, Long jobId);
}