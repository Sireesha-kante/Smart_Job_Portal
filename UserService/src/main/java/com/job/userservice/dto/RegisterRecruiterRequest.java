package com.job.userservice.dto;


import com.job.userservice.entity.User.Role;


public class RegisterRecruiterRequest {
	
	 private String username;
	    private String email;
	    private String password;
	    private String phone;
	    private String location;
	    private Role role;
	    private String companyName;
	    private String companyWebsite;
	    private String industry;
	    private String Companylocation;
	    
	    public RegisterRecruiterRequest() {
			// TODO Auto-generated constructor stub
		}

		public RegisterRecruiterRequest(String username, String email, String password, String phone, String location,
				Role role, String companyName, String companyWebsite, String industry, String companylocation) {
			super();
			this.username = username;
			this.email = email;
			this.password = password;
			this.phone = phone;
			this.location = location;
			this.role = role;
			this.companyName = companyName;
			this.companyWebsite = companyWebsite;
			this.industry = industry;
			Companylocation = companylocation;
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

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
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

		public String getCompanylocation() {
			return Companylocation;
		}

		public void setCompanylocation(String companylocation) {
			Companylocation = companylocation;
		}
	    
	    
}
