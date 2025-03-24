package com.job.jobservice.dto;

import com.job.jobservice.entity.JobPosting;
import com.job.jobservice.entity.JobApplication.ApplicationStatus;


public class JobApplicationDto {
	
    private Long id;

    private Long userId;

    private Long jobId;

    private ApplicationStatus status;
    
		public JobApplicationDto() {
			// TODO Auto-generated constructor stub
		}
		
		
		
		public JobApplicationDto(Long id, Long userId, Long jobId, ApplicationStatus status) {
			super();
			this.id = id;
			this.userId = userId;
			this.jobId = jobId;
			this.status = status;
		}



		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public Long getJobId() {
			return jobId;
		}
		public void setJobId(Long jobId) {
			this.jobId = jobId;
		}
		public ApplicationStatus getStatus() {
			return status;
		}
		public void setStatus(ApplicationStatus status) {
			this.status = status;
		}


    
}
