/*
 * package com.job.userservice.service;
 * 
 * import java.time.LocalDateTime; import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.job.userservice.entity.Notification; import
 * com.job.userservice.entity.User; import
 * com.job.userservice.repository.NotificationRepository;
 * 
 * @Service public class NotificationServiceImplementation implements
 * NotificationService {
 * 
 * @Autowired private NotificationRepository notificationRepository;
 * 
 * // Add a new notification for a user public Notification addNotification(User
 * user,String message) { Notification notification = new Notification( user,
 * message, false, LocalDateTime.now()); // false means unread return
 * notificationRepository.save(notification); }
 * 
 * // Get all notifications for a user public List<Notification>
 * getNotifications(User user) { return
 * notificationRepository.findByUserId(user.getId()); }
 * 
 * // Mark notification as read public Notification markAsRead(Long
 * notificationId) { Notification notification =
 * notificationRepository.findById(notificationId) .orElseThrow(() -> new
 * RuntimeException("Notification not found"));
 * 
 * notification.setRead(true); return notificationRepository.save(notification);
 * }
 * 
 * }
 */