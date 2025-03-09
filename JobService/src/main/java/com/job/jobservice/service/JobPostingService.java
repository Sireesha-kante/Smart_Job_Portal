package com.job.jobservice.service;

import java.util.List;

import com.job.jobservice.entity.JobPosting;


public interface JobPostingService {
	
	public JobPosting createJob(Long recruiterId, JobPosting jobPosting);
	  public List<JobPosting> getJobsByRecruiter(Long recruiterId);
	  public void deleteJob(Long jobId);
	    public List<JobPosting> searchJobs(String title, String location, String category);

}
