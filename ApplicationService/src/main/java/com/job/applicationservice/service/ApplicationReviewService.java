package com.job.applicationservice.service;

import java.util.List;

import com.job.applicationservice.entity.ApplicationReview;

public interface ApplicationReviewService {

    List<ApplicationReview> getApplications(Long recruiterId);
    ApplicationReview updateApplicationStatus(Long applicationId, ApplicationReview.ReviewStatus status);

}

