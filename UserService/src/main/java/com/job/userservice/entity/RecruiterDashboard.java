package com.job.userservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recruiter_dashboard")
public class RecruiterDashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private User recruiter;

    private long totalJobPostings;
    private long totalApplications;
    private long scheduledInterviews;

    
    public RecruiterDashboard() {
        super();
    }


	public RecruiterDashboard(Long id, User recruiter, long totalJobPostings, long totalApplications,
			long scheduledInterviews) {
		super();
		this.id = id;
		this.recruiter = recruiter;
		this.totalJobPostings = totalJobPostings;
		this.totalApplications = totalApplications;
		this.scheduledInterviews = scheduledInterviews;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getRecruiter() {
		return recruiter;
	}


	public void setRecruiter(User recruiter) {
		this.recruiter = recruiter;
	}


	public long getTotalJobPostings() {
		return totalJobPostings;
	}


	public void setTotalJobPostings(long totalJobPostings) {
		this.totalJobPostings = totalJobPostings;
	}


	public long getTotalApplications() {
		return totalApplications;
	}


	public void setTotalApplications(long totalApplications) {
		this.totalApplications = totalApplications;
	}


	public long getScheduledInterviews() {
		return scheduledInterviews;
	}


	public void setScheduledInterviews(long scheduledInterviews) {
		this.scheduledInterviews = scheduledInterviews;
	}

  
}
