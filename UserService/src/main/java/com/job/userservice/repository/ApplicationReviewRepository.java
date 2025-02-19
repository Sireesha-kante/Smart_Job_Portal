package com.job.userservice.repository;

import com.job.userservice.entity.ApplicationReview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationReviewRepository extends JpaRepository<ApplicationReview, Long> {
    List<ApplicationReview> findByRecruiterId(Long recruiterId);
}
