package com.job.jobservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.job.jobservice.dto.JobPostingDto;
import com.job.jobservice.exception.JobAlreadyExistsException;
import com.job.jobservice.exception.JobNotFoundException;
import com.job.jobservice.service.JobPostingService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/api/jobs")
public class JobPostingController {

    private final JobPostingService jobPostingService;
    
    private static final Logger logger = LoggerFactory.getLogger(JobPostingController.class);


    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @PostMapping("/create/{recruiterId}")
    public ResponseEntity<?> createJob(@PathVariable Long recruiterId, @RequestBody JobPostingDto jobPostingDto) {
        try {
            JobPostingDto createdJob = jobPostingService.createJob(recruiterId, jobPostingDto);
            logger.info("Job created successfully: {}", createdJob);
            return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
        } catch (JobAlreadyExistsException e) {
            logger.error("Job creation failed: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recruiter/{recruiterId}")
    public ResponseEntity<?> getJobsByRecruiter(@PathVariable Long recruiterId) {
        try {
            List<JobPostingDto> jobs = jobPostingService.getJobsByRecruiter(recruiterId);
            logger.info("Jobs retrieved successfully for recruiter {}", recruiterId);
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        } catch (JobNotFoundException e) {
            logger.warn("No jobs found: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchJobs(@RequestParam(required = false) String title,
                                        @RequestParam(required = false) String location,
                                        @RequestParam(required = false) String category) {
        try {
            List<JobPostingDto> jobs = jobPostingService.searchJobs(title, location, category);
            logger.info("Jobs retrieved successfully based on search criteria");
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        } catch (JobNotFoundException e) {
            logger.warn("No matching jobs found: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable Long jobId) {
        try {
            jobPostingService.deleteJob(jobId);
            logger.info("Job deleted successfully with ID: {}", jobId);
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
        } catch (JobNotFoundException e) {
            logger.warn("Job not found: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error deleting job: {}", e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}