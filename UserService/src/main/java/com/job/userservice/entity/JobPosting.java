package com.job.userservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_postings")
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private RecruiterProfile recruiter;

    private String title;
    private String description;
    private String location;
    private String category;
    private double salary;
    private int experienceRequired;
    private boolean isActive;
    private LocalDateTime createdAt;
    
    
	public JobPosting() {
		super();
	}
	public JobPosting(Long id, RecruiterProfile recruiter, String title, String description, String location,
			String category, double salary, int experienceRequired, boolean isActive, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.recruiter = recruiter;
		this.title = title;
		this.description = description;
		this.location = location;
		this.category = category;
		this.salary = salary;
		this.experienceRequired = experienceRequired;
		this.isActive = isActive;
		this.createdAt = createdAt;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getExperienceRequired() {
		return experienceRequired;
	}
	public void setExperienceRequired(int experienceRequired) {
		this.experienceRequired = experienceRequired;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
    
}
