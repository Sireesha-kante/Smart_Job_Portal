package com.job.resumeservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resume_processing_status")
public class ResumeDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    
    private String resumePath; // Stored path of uploaded resume
    
    private String extractedSkills;
    
    private String extractedExperience;
    
    @Enumerated(EnumType.STRING)
    private Status status; // PROCESSING, COMPLETED, FAILED
    
    public enum Status {
        PROCESSING, COMPLETED, FAILED
    }
    

	public ResumeDetails() {
		super();
	}


	public ResumeDetails(Long id, Long userId, String resumePath, String extractedSkills, String extractedExperience,
			Status status) {
		super();
		this.id = id;
		this.userId = userId;
		this.resumePath = resumePath;
		this.extractedSkills = extractedSkills;
		this.extractedExperience = extractedExperience;
		this.status = status;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getResumePath() {
		return resumePath;
	}


	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}


	public String getExtractedSkills() {
		return extractedSkills;
	}


	public void setExtractedSkills(String extractedSkills) {
		this.extractedSkills = extractedSkills;
	}


	public String getExtractedExperience() {
		return extractedExperience;
	}


	public void setExtractedExperience(String extractedExperience) {
		this.extractedExperience = extractedExperience;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
    
    
}
