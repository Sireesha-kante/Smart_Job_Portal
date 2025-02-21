package com.job.userservice.dto;

import com.job.userservice.entity.User.Role;

public class AuthResponse {
	
	 private String token;
	    private Role role;
		public AuthResponse(String token, Role role) {
			super();
			this.token = token;
			this.role = role;
		}
		
	public AuthResponse() {
		// TODO Auto-generated constructor stub
	}

	public AuthResponse(String jwtToken) {
		this.token=jwtToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	    
	   
}
