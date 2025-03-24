package com.job.userservice.dto;

public class UpdateRequest {

	private String username;
    private String email;
    private String password;
    private String phone;
    private String location;
    
    public UpdateRequest() {
		// TODO Auto-generated constructor stub
	}
    
    

	public UpdateRequest(String username, String email, String password, String phone, String location) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.location = location;
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
    
    
   

}
