package com.rev_hire.service;

import com.rev_hire.model.Notification;
import java.util.List;

public interface INotificationService {

    boolean sendNotification(Notification notification);
    List<Notification> getUserNotifications(int userId);
    boolean markAsRead(int notificationId);
    boolean deleteNotification(int notificationId);
}
