package com.job.applicationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job.applicationservice.entity.JobApplication;


@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUserId(Long userId);
    List<JobApplication> findByUserIdAndStatus(Long userId, JobApplication.ApplicationStatus status);
	boolean existsByUserIdAndJobId(Long id, Long jobId);
}
