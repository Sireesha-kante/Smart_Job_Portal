package com.job.userservice.dto;

import java.util.List;
import java.util.Set;

import com.job.userservice.entity.User.Role;

import jakarta.persistence.ElementCollection;

public class RegisterRequest {
	
	 private String username;
	    private String email;
	    private String password;
	    private Role role;
	    private String phone;
	    private String location;
	    private String resumeUrl;
	    private Set<String> skills; 
	    private String bio;
	    private String linkedinUrl;
	    private String githubUrl;
	    private String companyName;
	    private String companyWebsite;
	    private String industry;

	    @ElementCollection
	    private List<String> education;  // List of degrees/certifications

	    @ElementCollection
	    private List<String> experience;  // List of past job roles

	    @ElementCollection
	    private List<String> preferredJobCategories;

	    
	    
		public RegisterRequest() {
			super();
		}

		public RegisterRequest(String username, String email, String password, Role role, String phone, String location,
				String resumeUrl, Set<String> skills, String bio, String linkedinUrl, String githubUrl,
				List<String> education, List<String> experience, List<String> preferredJobCategories) {
			super();
			this.username = username;
			this.email = email;
			this.password = password;
			this.role = role;
			this.phone = phone;
			this.location = location;
			this.resumeUrl = resumeUrl;
			this.skills = skills;
			this.bio = bio;
			this.linkedinUrl = linkedinUrl;
			this.githubUrl = githubUrl;
			this.education = education;
			this.experience = experience;
			this.preferredJobCategories = preferredJobCategories;
		}
     
		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getCompanyWebsite() {
			return companyWebsite;
		}

		public void setCompanyWebsite(String companyWebsite) {
			this.companyWebsite = companyWebsite;
		}

		public String getIndustry() {
			return industry;
		}

		public void setIndustry(String industry) {
			this.industry = industry;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
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
