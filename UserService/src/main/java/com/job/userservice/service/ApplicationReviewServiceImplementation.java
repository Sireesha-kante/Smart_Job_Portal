package com.job.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.job.userservice.entity.ApplicationReview;
import com.job.userservice.exception.ResourceNotFoundException;
import com.job.userservice.repository.ApplicationReviewRepository;
import com.job.userservice.repository.RecruiterProfileRepository;

@Service
public class ApplicationReviewServiceImplementation implements AppliationReviewService {

    @Autowired
    private ApplicationReviewRepository applicationReviewRepository;
    
    @Autowired
    private RecruiterProfileRepository recruiterProfileRepository;

    @Override
    public List<ApplicationReview> getApplications(Long recruiterId) {
        if (!recruiterProfileRepository.existsById(recruiterId)) {
            throw new ResourceNotFoundException("Recruiter not found with ID: " + recruiterId);
        }
        return applicationReviewRepository.findByRecruiterId(recruiterId);
    }

    @Override
    @Transactional
    public ApplicationReview updateApplicationStatus(Long applicationId, ApplicationReview.ReviewStatus status) {
        ApplicationReview applicationReview = applicationReviewRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application review not found with ID: " + applicationId));
        
        applicationReview.setStatus(status);
        return applicationReviewRepository.save(applicationReview);
    }
}
