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
		
		public AuthResponse(String token) {
			super();
			this.token = token;
		}
		public AuthResponse() {
			super();
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
	    
	    

}
