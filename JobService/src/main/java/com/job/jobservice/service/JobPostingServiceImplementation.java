package com.job.jobservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.job.jobservice.entity.JobPosting;
import com.job.jobservice.repository.JobPostingRepository;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.stereotype.Service;


@Service
public class JobPostingServiceImplementation implements JobPostingService {

	@Autowired
	private JobPostingRepository jobPostingRepository;
	@Autowired
	private KafkaOperations<String, String> kafkaOperations;
	
	@Override
	public JobPosting createJob(Long recruiterId, JobPosting jobPosting) {
		jobPosting.setRecruiter(recruiterId);
		 JobPosting savedJob=jobPostingRepository.save(jobPosting);
		 kafkaOperations.send("job-events","JOB_POSTED", recruiterId.toString());
	  return savedJob;
	}

	@Override
	public List<JobPosting> getJobsByRecruiter(Long recruiterId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteJob(Long jobId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<JobPosting> searchJobs(String title, String location, String category) {
		// TODO Auto-generated method stub
		return null;
	}

}
