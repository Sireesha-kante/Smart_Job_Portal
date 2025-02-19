package com.job.userservice.dto;

public class RecruiterDashboardDto {
    private long totalJobPostings;
    private long totalApplications;
    private long scheduledInterviews;
    
    
	public RecruiterDashboardDto() {
		super();
	}
	public RecruiterDashboardDto(long totalJobPostings, long totalApplications, long scheduledInterviews) {
		super();
		this.totalJobPostings = totalJobPostings;
		this.totalApplications = totalApplications;
		this.scheduledInterviews = scheduledInterviews;
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
