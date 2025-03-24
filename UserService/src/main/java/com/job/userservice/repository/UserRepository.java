package com.job.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.job.userservice.entity.User;

@Repository
public interface UserRepository extends JpaRepositoryImplementation<User, Long> {

	User findByEmail(String email);
	 Optional<User> findByUsername(String username);
	 void deleteById(Long userId);
}
