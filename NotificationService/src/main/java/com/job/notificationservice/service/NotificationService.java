package com.job.notificationservice.service;

import java.util.List;

import com.job.notificationservice.entity.Notification;


public interface NotificationService {
	 public Notification addNotification(User user, String message);
	 
	 public List<Notification> getNotifications(User user);
	 
	 public Notification markAsRead(Long notificationId);

}
