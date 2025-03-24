package com.job.jobservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class JobApplication {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "job_id", nullable = false)
    private Long jobId;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    
    public enum ApplicationStatus {
        UNDERCONSIDERATION,
        ACCEPTED,
        REJECTED,
        NO_LONGER_ACCEPTING_PROFILES,
        POSTITION_ON_HOLD,
        WITHDRAW_APPLICATION
        
    }

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id", insertable = false, updatable = false)
    private JobPosting jobPosting;
    
    
    public JobApplication() {
		// TODO Auto-generated constructor stub
	}


	public JobApplication(Long id, Long userId, Long jobId, ApplicationStatus status, JobPosting jobPosting) {
		super();
		this.id = id;
		this.userId = userId;
		this.jobId = jobId;
		this.status = status;
		this.jobPosting = jobPosting;
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


	public JobPosting getJobPosting() {
		return jobPosting;
	}


	public void setJobPosting(JobPosting jobPosting) {
		this.jobPosting = jobPosting;
	}
    
    
}
