package com.job.notificationservice.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 
    private Long userId;

    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;
    
    public Notification() {
		// TODO Auto-generated constructor stub
	}

	public Notification(Long id, Long userId, String message, boolean isRead, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.message = message;
		this.isRead = isRead;
		this.createdAt = createdAt;
	}
	
	public Notification(Long user, String message, boolean isRead, LocalDateTime createdAt) {
		super();
		this.userId = userId;
		this.message = message;
		this.isRead = isRead;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser() {
		return userId;
	}

	public void setUser(Long userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
    
}


