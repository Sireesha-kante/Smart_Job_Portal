package com.job.jobservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job.jobservice.entity.JobPosting;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
 
	  List<JobPosting> findByRecruiterId(Long recruiterId);
	    
	    Optional<JobPosting> findByTitleAndLocationAndCategory(String title, String location, String category);

	    List<JobPosting> findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCaseOrCategoryContainingIgnoreCase(String title, String location, String category);
	    
	    List<JobPosting> findByPostedBy(String email);
	    
	    Optional<JobPosting> findById(Long jobId);
}
