package com.rev_hire.service;

import com.rev_hire.dao.*;
import com.rev_hire.model.Notification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class NotificationServiceImpl implements INotificationService {

    private static final Logger logger = LogManager.getLogger(NotificationServiceImpl.class);
    private INotificationDao dao;

    public NotificationServiceImpl() {
        this.dao = new NotificationDaoImpl();
    }

    public NotificationServiceImpl(INotificationDao dao) {
        this.dao = dao;
    }

    public boolean sendNotification(Notification notification) {
        logger.info("Sending notification: {}", notification.getMessage());
        return dao.addNotification(notification);
    }

    public List<Notification> getUserNotifications(int userId) {
        logger.info("Fetching notifications for userId: {}", userId);
        return dao.getNotificationsByUser(userId);
    }

    public boolean markAsRead(int notificationId) {
        logger.debug("Marking notification as read: {}", notificationId);
        return dao.markAsRead(notificationId);
    }

    public boolean deleteNotification(int notificationId) {
        logger.info("Deleting notification: {}", notificationId);
        return dao.deleteNotification(notificationId);
    }
}
