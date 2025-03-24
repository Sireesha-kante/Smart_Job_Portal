package com.job.jobservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.job.jobservice.dto.JobPostingDto;
import com.job.jobservice.dto.JobPostingMapper;
import com.job.jobservice.entity.JobPosting;
import com.job.jobservice.exception.JobAlreadyExistsException;
import com.job.jobservice.exception.JobNotFoundException;
import com.job.jobservice.repository.JobPostingRepository;

import jakarta.transaction.Transactional;

import org.springframework.kafka.core.KafkaOperations;
import org.springframework.stereotype.Service;


@Service
public class JobPostingServiceImplementation implements JobPostingService {

	@Autowired
	private JobPostingRepository jobPostingRepository;
	
	@Autowired
	private JobPostingMapper jobPostingMapper;
	
//	@Autowired
//	private KafkaOperations<String, String> kafkaOperations;

	  @Override
	    public JobPostingDto createJob(Long recruiterId, JobPostingDto jobPostingDto) {
	        if (jobPostingDto == null) {
	            throw new IllegalArgumentException("Job posting data cannot be null");
	        }

	        jobPostingDto.setRecruiterId(recruiterId);

	        // Check if a similar job already exists
	        List<JobPosting> existingJob = jobPostingRepository.findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCaseOrCategoryContainingIgnoreCase(
	                jobPostingDto.getTitle(), jobPostingDto.getLocation(), jobPostingDto.getCategory()
	        );
	        if (existingJob!=null) {
	            throw new JobAlreadyExistsException("A similar job posting already exists.");
	        }

	        JobPosting jobPosting = jobPostingMapper.toEntity(jobPostingDto);
	        JobPosting savedJob = jobPostingRepository.save(jobPosting);
	        return jobPostingMapper.toDTO(savedJob);
	    }

	    @Override
	    public List<JobPostingDto> getJobsByRecruiter(Long recruiterId) {
	        List<JobPosting> jobs = jobPostingRepository.findByRecruiterId(recruiterId);
	        if (jobs.isEmpty()) {
	            throw new JobNotFoundException("No jobs found for recruiter ID: " + recruiterId);
	        }
	        return jobs.stream().map(jobPostingMapper::toDTO).collect(Collectors.toList());
	    }

	    @Override
	    public List<JobPostingDto> searchJobs(String title, String location, String category) {
	        List<JobPosting> jobs = jobPostingRepository.findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCaseOrCategoryContainingIgnoreCase(
	                title, location, category
	        );
	        if (jobs.isEmpty()) {
	            throw new JobNotFoundException("No matching jobs found for the given criteria.");
	        }
	        return jobs.stream().map(jobPostingMapper::toDTO).collect(Collectors.toList());
	    }

	    @Override
	    @Transactional
	    public void deleteJob(Long jobId) {
	        JobPosting job = jobPostingRepository.findById(jobId)
	                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + jobId));
	        jobPostingRepository.delete(job);
	    }


}
