package com.job.jobservice.dto;

import com.job.jobservice.entity.JobPosting;

public class JobPostingMapper {
	
	  public  JobPostingDto toDTO(JobPosting jobPosting) {
		  
		  if (jobPosting == null) {
	            throw new IllegalArgumentException("JobPostingDTO cannot be null");
	        }
	        
	        return new JobPostingDto(
	                jobPosting.getId(),
	                jobPosting.getRecruiterId(),
	                jobPosting.getTitle(),
	                jobPosting.getDescription(),
	                jobPosting.getLocation(),
	                jobPosting.getCategory(),
	                jobPosting.getSalary(),
	                jobPosting.getExperienceRequired(),
	                jobPosting.getCompany(),
	                jobPosting.isActive(),
	                jobPosting.getCreatedAt(),
	                jobPosting.getPostedBy()
	        );
	    }

	    // Convert JobPostingDTO to JobPosting Entity
	    public  JobPosting toEntity(JobPostingDto dto) {
	    	
	    	 if (dto == null) {
	             throw new IllegalArgumentException("JobPosting entity cannot be null");
	         }
	        JobPosting jobPosting = new JobPosting();
	        jobPosting.setId(dto.getId());
	        jobPosting.setRecruiterId(dto.getRecruiterId());
	        jobPosting.setTitle(dto.getTitle());
	        jobPosting.setDescription(dto.getDescription());
	        jobPosting.setLocation(dto.getLocation());
	        jobPosting.setCategory(dto.getCategory());
	        jobPosting.setSalary(dto.getSalary());
	        jobPosting.setExperienceRequired(dto.getExperienceRequired());
	        jobPosting.setCompany(dto.getCompany());
	        jobPosting.setActive(dto.isActive());
	        jobPosting.setCreatedAt(dto.getCreatedAt());
	        jobPosting.setPostedBy(dto.getPostedBy());
	        return jobPosting;
	    }
}
