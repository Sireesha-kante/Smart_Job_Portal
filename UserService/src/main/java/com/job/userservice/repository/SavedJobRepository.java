package com.job.userservice.repository;

import com.job.userservice.entity.SavedJob;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {
    List<SavedJob> findByUserId(Long userId);

  Optional<SavedJob> findByUserIdAndJobId(Long userId, Long jobId);
  boolean existsByUserIdAndJobId(Long userId,Long jobId);
  
  @Transactional
  void deleteByUserIdAndJobId(Long userId, Long jobId);
}
