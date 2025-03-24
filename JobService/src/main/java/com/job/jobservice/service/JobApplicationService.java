package com.job.jobservice.service;

import java.util.List;

import com.job.jobservice.dto.JobApplicationDto;
import com.job.jobservice.dto.JobPostingDto;

public interface JobApplicationService {
	
	  public JobApplicationDto applyForJob(Long userId, Long jobId);
	  public List<JobPostingDto> getAppliedJobs(Long userId);

}
