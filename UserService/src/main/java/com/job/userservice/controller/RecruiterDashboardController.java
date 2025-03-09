package com.job.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.userservice.dto.RecruiterDashboardDto;
import com.job.userservice.exception.ResourceNotFoundException;
import com.job.userservice.service.RecruiterDashboardService;

@RestController
@RequestMapping("/api/recruiter/dashboard")
public class RecruiterDashboardController {
	
	@Autowired
    private static final Logger logger = LoggerFactory.getLogger(RecruiterController.class);
	
	@Autowired
	private  RecruiterDashboardService recruiterDashboardService;
	
	   @GetMapping("/dashboard/{recruiterId}")
	    @PreAuthorize("hasRole('RECRUITER')")
	    public ResponseEntity<?> getRecruiterDashboard(@PathVariable Long recruiterId) {
	        try {
	            return ResponseEntity.ok(recruiterDashboardService.getRecruiterDashboard(recruiterId));
	        } catch (ResourceNotFoundException ex) {
	            logger.error("Dashboard not found: {}", ex.getMessage());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	        } catch (Exception ex) {
	            logger.error("Error retrieving dashboard", ex);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching dashboard data");
	        }
	   }
	        
	
	@PutMapping("/{recruiterId}/increment-applications")
	public ResponseEntity<String> incrementTotalApplications(@PathVariable  Long recruiterId){
		recruiterDashboardService.incrementTotalApplications(recruiterId);
		return ResponseEntity.ok("dashboard applications upd ate");
	}
	
	@PutMapping("/{recruiterId}/increment-jobposting")
	public ResponseEntity<String> incrementJobPosting(@PathVariable  Long recruiterId){
		recruiterDashboardService.incrementJobPostings(recruiterId);
		return ResponseEntity.ok("dashboard  jobposts  update");
	}
	
	@PutMapping("/{recruiterId}/increment-interviews")
	public ResponseEntity<String> incrementInterviews(@PathVariable  Long recruiterId){
		recruiterDashboardService.incrementScheduledInterviews(recruiterId);
		return ResponseEntity.ok("dashboard  interview update");
	}
	
	
	
    

}
