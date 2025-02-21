package com.job.userservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Long jobId;  // Reference to Job (from another service)
    
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;  

    private LocalDateTime appliedAt;

    public enum ApplicationStatus {
        PENDING, REVIEWED, INTERVIEW_SCHEDULED, REJECTED, OFFERED
    }
    
    

	public JobApplication() {
		super();
	}

	public JobApplication(Long id, User user, Long jobId, ApplicationStatus status, LocalDateTime appliedAt) {
		super();
		this.id = id;
		this.user = user;
		this.jobId = jobId;
		this.status = status;
		this.appliedAt = appliedAt;
	}
	
	public JobApplication(User user, ApplicationStatus status, LocalDateTime appliedAt) {
		super();
		this.user = user;
		this.status = status;
		this.appliedAt = appliedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}

	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}
    
}
