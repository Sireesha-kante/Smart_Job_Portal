package com.job.jobservice.service;

import java.util.List;

import com.job.jobservice.dto.JobPostingDto;

public interface JobPostingService {
	    
	JobPostingDto createJob(Long recruiterId, JobPostingDto jobPostingDto);
	    List<JobPostingDto> getJobsByRecruiter(Long recruiterId);
	    List<JobPostingDto> searchJobs(String title, String location, String category);
	    void deleteJob(Long jobId);
	    

}
