package com.job.userservice.service;

import java.util.List;

import com.job.userservice.entity.SavedJob;
import com.job.userservice.entity.User;

public interface SavedJobService {

	 public SavedJob saveJob(User user, Long jobId);
	 
	 public List<SavedJob> getSavedJobs(Long userId);
	 
	 public void removeSavedJob(Long userId, Long jobId);
	 
}
