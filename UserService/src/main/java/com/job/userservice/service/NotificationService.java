package com.job.userservice.service;

import java.util.List;

import com.job.userservice.entity.Notification;

public interface NotificationService {
	 public Notification addNotification(Long userId, String message);
	 
	 public List<Notification> getNotifications(Long userId);
	 
	 public Notification markAsRead(Long notificationId);

}
