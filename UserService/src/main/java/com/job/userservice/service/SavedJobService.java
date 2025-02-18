package com.job.userservice.service;

import java.util.List;

import com.job.userservice.entity.SavedJob;

public interface SavedJobService {

	 public SavedJob saveJob(Long userId, Long jobId);
	 
	 public List<SavedJob> getSavedJobs(Long userId);
	 
	 public void removeSavedJob(Long userId, Long jobId);
	 
}
