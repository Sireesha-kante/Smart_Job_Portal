package com.job.userservice.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String bio;
    private String linkedinUrl;
    private String githubUrl;
    
    private String resumeUrl;

    @ElementCollection
    private Set<String> skills; 

    @ElementCollection
    private List<String> education;  // List of degrees/certifications

    @ElementCollection
    private List<String> experience;  // List of past job roles

    @ElementCollection
    private List<String> preferredJobCategories;
    
    

	public UserProfile() {
		super();
	}

	public UserProfile(Long id, String email, User user, String bio, String linkedinUrl, String githubUrl,
			String resumeUrl, Set<String> skills, List<String> education, List<String> experience,
			List<String> preferredJobCategories) {
		super();
		this.id = id;
		this.email = email;
		this.user = user;
		this.bio = bio;
		this.linkedinUrl = linkedinUrl;
		this.githubUrl = githubUrl;
		this.resumeUrl = resumeUrl;
		this.skills = skills;
		this.education = education;
		this.experience = experience;
		this.preferredJobCategories = preferredJobCategories;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getLinkedinUrl() {
		return linkedinUrl;
	}

	public void setLinkedinUrl(String linkedinUrl) {
		this.linkedinUrl = linkedinUrl;
	}

	public String getGithubUrl() {
		return githubUrl;
	}

	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}

	public String getResumeUrl() {
		return resumeUrl;
	}

	public void setResumeUrl(String resumeUrl) {
		this.resumeUrl = resumeUrl;
	}

	public Set<String> getSkills() {
		return skills;
	}

	public void setSkills(Set<String> skills) {
		this.skills = skills;
	}

	public List<String> getEducation() {
		return education;
	}

	public void setEducation(List<String> education) {
		this.education = education;
	}

	public List<String> getExperience() {
		return experience;
	}

	public void setExperience(List<String> experience) {
		this.experience = experience;
	}

	public List<String> getPreferredJobCategories() {
		return preferredJobCategories;
	}

	public void setPreferredJobCategories(List<String> preferredJobCategories) {
		this.preferredJobCategories = preferredJobCategories;
	}

	
}

