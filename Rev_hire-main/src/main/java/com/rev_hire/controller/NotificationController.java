package com.rev_hire.controller;

import com.rev_hire.model.Notification;
import com.rev_hire.service.*;

import java.util.List;

public class NotificationController {

    private static INotificationService service = new NotificationServiceImpl();

    public boolean send(Notification notification) {
        return service.sendNotification(notification);
    }

    public List<Notification> getMyNotifications(int userId) {
        return service.getUserNotifications(userId);
    }

    public boolean markAsRead(int notificationId) {
        return service.markAsRead(notificationId);
    }

    public boolean delete(int notificationId) {
        return service.deleteNotification(notificationId);
    }
}
