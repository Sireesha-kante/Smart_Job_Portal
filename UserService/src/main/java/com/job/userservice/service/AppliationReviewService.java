package com.job.userservice.service;

import java.util.List;

import com.job.userservice.entity.ApplicationReview;

public interface AppliationReviewService {

     List<ApplicationReview> getApplications(Long recruiterId);
     ApplicationReview updateApplicationStatus(Long applicationId, ApplicationReview.ReviewStatus status);

}
