package com.job.userservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private RecruiterProfile recruiter;

    @OneToOne
    @JoinColumn(name = "job_application_id", nullable = false)
    private JobApplication jobApplication;

    private LocalDateTime interviewDate;
    private String interviewLink;
    
    
    public Interview() {
		super();
	}

	public Interview(Long id, RecruiterProfile recruiter, JobApplication jobApplication, LocalDateTime interviewDate,
			String interviewLink) {
		super();
		this.id = id;
		this.recruiter = recruiter;
		this.jobApplication = jobApplication;
		this.interviewDate = interviewDate;
		this.interviewLink = interviewLink;
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

	public LocalDateTime getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(LocalDateTime interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getInterviewLink() {
		return interviewLink;
	}

	public void setInterviewLink(String interviewLink) {
		this.interviewLink = interviewLink;
	}
    
}
