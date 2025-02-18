package com.job.userservice.dto;

import com.job.userservice.entity.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class UserDashboardDto {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @OneToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user;

	    private int totalApplications;
	    private int savedJobs;
	    private int unreadNotifications;
	    
	    public UserDashboardDto() {
			// TODO Auto-generated constructor stub
		}

		public UserDashboardDto(Long id, User user, int totalApplications, int savedJobs, int unreadNotifications) {
			super();
			this.id = id;
			this.user = user;
			this.totalApplications = totalApplications;
			this.savedJobs = savedJobs;
			this.unreadNotifications = unreadNotifications;
		}
		
		

		public UserDashboardDto(int totalApplications, int savedJobs, int unreadNotifications) {
			super();
			this.totalApplications = totalApplications;
			this.savedJobs = savedJobs;
			this.unreadNotifications = unreadNotifications;
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

		public int getTotalApplications() {
			return totalApplications;
		}

		public void setTotalApplications(int totalApplications) {
			this.totalApplications = totalApplications;
		}

		public int getSavedJobs() {
			return savedJobs;
		}

		public void setSavedJobs(int savedJobs) {
			this.savedJobs = savedJobs;
		}

		public int getUnreadNotifications() {
			return unreadNotifications;
		}

		public void setUnreadNotifications(int unreadNotifications) {
			this.unreadNotifications = unreadNotifications;
		}
	    
	    

}
