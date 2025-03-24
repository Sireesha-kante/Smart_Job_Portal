package com.job.jobservice.dto;

import java.time.LocalDate;

public class JobPostingDto {
    
	private Long id;

    private Long recruiterId;

    private String title;
    private String description;
    private String location;
    private String category;
    private double salary;
    private int experienceRequired;
    private String Company;
    private boolean isActive;
    private  LocalDate createdAt;
    private String postedBy;
    
    public JobPostingDto() {
		// TODO Auto-generated constructor stub
	}

    
	public JobPostingDto(Long id, Long recruiterId, String title, String description, String location, String category,
			double salary, int experienceRequired, String company, boolean isActive, LocalDate createdAt,
			String postedBy) {
		super();
		this.id = id;
		this.recruiterId = recruiterId;
		this.title = title;
		this.description = description;
		this.location = location;
		this.category = category;
		this.salary = salary;
		this.experienceRequired = experienceRequired;
		Company = company;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.postedBy = postedBy;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(Long recruiterId) {
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

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	
    
}
