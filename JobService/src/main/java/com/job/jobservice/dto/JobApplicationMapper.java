package com.job.jobservice.dto;

import com.job.jobservice.entity.JobApplication;

public class JobApplicationMapper {

	
	   // Convert JobApplication Entity to JobApplicationDTO
    public  JobApplicationDto toDTO(JobApplication jobApplication) {
        return new JobApplicationDto(
                jobApplication.getId(),
                jobApplication.getUserId(),
                jobApplication.getJobId(),
                jobApplication.getStatus()
        );
    }
    
    public JobApplication toEntity(Long userId, Long jobId) {
        JobApplication jobApplication = new JobApplication();
        jobApplication.setUserId(userId);
        jobApplication.setJobId(jobId);
        return jobApplication;
    }

    // Convert JobApplicationDTO to JobApplication Entity
    public  JobApplication toEntity(JobApplicationDto dto) {
        JobApplication jobApplication = new JobApplication();
        jobApplication.setId(dto.getId());
        jobApplication.setUserId(dto.getUserId());
        jobApplication.setJobId(dto.getJobId());
        jobApplication.setStatus(dto.getStatus());
        return jobApplication;
    }
}
