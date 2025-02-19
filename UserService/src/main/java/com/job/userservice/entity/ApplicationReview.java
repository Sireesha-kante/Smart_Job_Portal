package com.job.userservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "application_reviews")
public class ApplicationReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private RecruiterProfile recruiter;

    @OneToOne
    @JoinColumn(name = "job_application_id", nullable = false)
    private JobApplication jobApplication;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;
    
    public ApplicationReview() {
		// TODO Auto-generated constructor stub
	}
    

    public ApplicationReview(Long id, RecruiterProfile recruiter, JobApplication jobApplication, ReviewStatus status) {
		super();
		this.id = id;
		this.recruiter = recruiter;
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

	public RecruiterProfile getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(RecruiterProfile recruiter) {
		this.recruiter = recruiter;
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
