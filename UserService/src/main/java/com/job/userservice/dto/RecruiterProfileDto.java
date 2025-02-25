package com.job.userservice.dto;

import com.job.userservice.entity.User;


public class RecruiterProfileDto {

	    private Long id;

	    private User user;  // Linked to User entity

	    private String companyName;
	    private String companyWebsite;
	    private String industry;
	    private String location;
	    
	    
	    public RecruiterProfileDto() {
			// TODO Auto-generated constructor stub
		}
	    
		public RecruiterProfileDto(Long id, User user, String companyName, String companyWebsite, String industry,
				String location) {
			super();
			this.id = id;
			this.user = user;
			this.companyName = companyName;
			this.companyWebsite = companyWebsite;
			this.industry = industry;
			this.location = location;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
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

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}
	     
	    
}
