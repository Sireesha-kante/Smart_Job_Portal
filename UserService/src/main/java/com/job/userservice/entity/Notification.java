package com.job.userservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;
    
    public Notification() {
		// TODO Auto-generated constructor stub
	}

	public Notification(Long id, User user, String message, boolean isRead, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.user = user;
		this.message = message;
		this.isRead = isRead;
		this.createdAt = createdAt;
	}
	
	public Notification(User user, String message, boolean isRead, LocalDateTime createdAt) {
		super();
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

