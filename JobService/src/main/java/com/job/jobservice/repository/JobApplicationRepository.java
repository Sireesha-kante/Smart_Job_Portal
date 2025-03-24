package com.job.jobservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.jobservice.entity.JobApplication;


public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

	 Optional<JobApplication> findByUserIdAndJobId(Long userId, Long jobId);
	   List<JobApplication> findByUserId(Long userId); 
}
