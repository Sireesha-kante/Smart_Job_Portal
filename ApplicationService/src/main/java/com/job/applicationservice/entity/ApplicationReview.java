package com.job.applicationservice.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "application_reviews")
public class ApplicationReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long recruiterId;

    @OneToOne
    @JoinColumn(name = "job_application_id", nullable = false)
    private JobApplication jobApplication;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;
    
    public ApplicationReview() {
	}
    

    public ApplicationReview(Long id, Long recruiterId, JobApplication jobApplication, ReviewStatus status) {
		super();
		this.id = id;
		this.recruiterId = recruiterId;
		this.jobApplication = jobApplication;
		this.status = status;
	}



	public enum ReviewStatus {
        SHORTLISTED, REJECTED, INTERVIEW_SCHEDULED
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRecruiter() {
		return recruiterId;
	}

	public void setRecruiter(Long recruiterId) {
		this.recruiterId = recruiterId;
	}

	public JobApplication getJobApplication() {
		return jobApplication;
	}

	public void setJobApplication(JobApplication jobApplication) {
		this.jobApplication = jobApplication;
	}

	public ReviewStatus getStatus() {
		return status;
	}

	public void setStatus(ReviewStatus status) {
		this.status = status;
	}
    
    
}
