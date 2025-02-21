package com.job.userservice.service;

import java.util.List;

import com.job.userservice.entity.Notification;
import com.job.userservice.entity.User;

public interface NotificationService {
	 public Notification addNotification(User user, String message);
	 
	 public List<Notification> getNotifications(User user);
	 
	 public Notification markAsRead(Long notificationId);

}
