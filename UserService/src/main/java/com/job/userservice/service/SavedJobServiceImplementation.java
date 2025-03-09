package com.job.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.userservice.entity.SavedJob;
import com.job.userservice.entity.User;
import com.job.userservice.repository.SavedJobRepository;

@Service
public class SavedJobServiceImplementation implements SavedJobService {

    @Autowired
    private SavedJobRepository savedJobRepository;

    @Override
    public SavedJob saveJob(User user, Long jobId) {
        SavedJob savedJob = new SavedJob(user);
        return savedJobRepository.save(savedJob);
    }

    @Override
    public List<SavedJob> getSavedJobs(Long userId) {
        return savedJobRepository.findByUserId(userId);
    }

    @Override
    public void removeSavedJob(Long userId, Long jobId) {
        SavedJob savedJob = savedJobRepository.findByUserIdAndJobId(userId, jobId)
                .orElseThrow(() -> new RuntimeException("Saved job not found"));
        savedJobRepository.delete(savedJob);
    }
}
