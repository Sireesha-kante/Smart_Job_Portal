package com.job.jobservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_postings")
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long recruiterId;

    private String title;
    private String description;
    private String location;
    private String category;
    private double salary;
    private int experienceRequired;
    private boolean isActive;
    private LocalDateTime createdAt;
    
	public JobPosting(Long id, Long recruiterId, String title, String description, String location,
			String category, double salary, int experienceRequired, boolean isActive, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.recruiterId = recruiterId;
		this.title = title;
		this.description = description;
		this.location = location;
		this.category = category;
		this.salary = salary;
		this.experienceRequired = experienceRequired;
		this.isActive = isActive;
		this.createdAt = createdAt;
	}
	
	public JobPosting(Long recruiterId, String title, String description, String location,
			String category, double salary, int experienceRequired, boolean isActive, LocalDateTime createdAt) {
		super();
		
		this.recruiterId = recruiterId;
		this.title = title;
		this.description = description;
		this.location = location;
		this.category = category;
		this.salary = salary;
		this.experienceRequired = experienceRequired;
		this.isActive = true;
		this.createdAt = LocalDateTime.now();
	}
	
	  public JobPosting() {
	       this.createdAt = LocalDateTime.now(); // Set default created time
	        this.isActive = true; // Default to active
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
