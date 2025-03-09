package com.job.userservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_dashboard")
public class UserDashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true, nullable = false)
    private User user;

    private int totalApplications;
    private int savedJobs;
    private int unreadNotifications;

    // Default constructor (required by JPA)
    public UserDashboard() {}

    // Constructor including 'user'
    public UserDashboard(User user, int totalApplications, int savedJobs, int unreadNotifications) {
        this.user = user;
        this.totalApplications = totalApplications;
        this.savedJobs = savedJobs;
        this.unreadNotifications = unreadNotifications;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public int getTotalApplications() { return totalApplications; }
    public void setTotalApplications(int totalApplications) { this.totalApplications = totalApplications; }

    public int getSavedJobs() { return savedJobs; }
    public void setSavedJobs(int savedJobs) { this.savedJobs = savedJobs; }

    public int getUnreadNotifications() { return unreadNotifications; }
    public void setUnreadNotifications(int unreadNotifications) { this.unreadNotifications = unreadNotifications; }
}
