package com.job.userservice.repository;

import com.job.userservice.entity.UserProfile;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	
	@Cacheable(value="userProfile", key="userId", unless = "#result==null")
    Optional<UserProfile> findByUserId(Long userId);
	@Cacheable(value="userProfile", key="email", unless = "#result==null")
    UserProfile findByEmail(String Email);
}
