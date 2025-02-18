package com.job.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.job.userservice.entity.User;

public interface UserRepository extends JpaRepositoryImplementation<User, Long> {

	User findByEmail(String email);
	 Optional<User> findByUsername(String username);
}
