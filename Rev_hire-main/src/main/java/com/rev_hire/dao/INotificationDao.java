package com.rev_hire.dao;

import com.rev_hire.model.Notification;
import java.util.List;

public interface INotificationDao {

    boolean addNotification(Notification notification);
    List<Notification> getNotificationsByUser(int userId);
    boolean markAsRead(int notificationId);
    boolean deleteNotification(int notificationId);
}
