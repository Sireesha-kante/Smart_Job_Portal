package com.job.applicationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job.applicationservice.entity.ApplicationReview;

@Repository
public interface ApplicationReviewRepository extends JpaRepository<ApplicationReview, Long> {
    List<ApplicationReview> findByRecruiterId(Long recruiterId);
}
