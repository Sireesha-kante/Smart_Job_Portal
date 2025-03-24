package com.job.jobservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.job.jobservice.dto.JobApplicationDto;
import com.job.jobservice.dto.JobPostingDto;
import com.job.jobservice.service.JobApplicationService;

public class JobApplicationController {
	

    private static final Logger logger = LoggerFactory.getLogger(JobApplicationController.class);

    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping("/apply/{userId}/{jobId}")
    public ResponseEntity<JobApplicationDto> applyForJob(@PathVariable Long userId, @PathVariable Long jobId) {
        logger.info("User {} is applying for Job {}", userId, jobId);
        try {
            JobApplicationDto appliedJob = jobApplicationService.applyForJob(userId, jobId);
            logger.info("Application successful for User {} on Job {}", userId, jobId);
            return ResponseEntity.ok(appliedJob);
        } catch (RuntimeException e) {
            logger.error("Error applying for job: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/applied/{userId}")
    public ResponseEntity<List<JobPostingDto>> getAppliedJobs(@PathVariable Long userId) {
        logger.info("Fetching applied jobs for User {}", userId);
        try {
            List<JobPostingDto> appliedJobs = jobApplicationService.getAppliedJobs(userId);
            return ResponseEntity.ok(appliedJobs);
        } catch (RuntimeException e) {
            logger.error("Error fetching applied jobs: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}
