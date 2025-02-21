package com.job.userservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "saved_jobs")

public class SavedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Long jobId;  // Reference to Job (from Job Service)

    
	public SavedJob() {
		super();
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

	public SavedJob(Long id, User user, Long jobId) {
		super();
		this.id = id;
		this.user = user;
		this.jobId = jobId;
	}

	public SavedJob(User user ) {
		
		this.user=user;
		
	}

    
    
}
