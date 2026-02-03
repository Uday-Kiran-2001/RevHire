package com.rev_hire.service;

import com.rev_hire.dao.INotificationDao;
import com.rev_hire.model.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private INotificationDao notificationDao;

    private NotificationServiceImpl notificationService;

    @BeforeEach
    void setUp() {
        notificationService = new NotificationServiceImpl(notificationDao);
    }

    @Test
    void testSendNotification() {
        Notification note = new Notification();
        note.setMessage("Welcome");
        when(notificationDao.addNotification(any(Notification.class))).thenReturn(true);

        boolean result = notificationService.sendNotification(note);
        assertTrue(result);
    }

    @Test
    void testGetUserNotifications() {
        when(notificationDao.getNotificationsByUser(100))
                .thenReturn(Arrays.asList(new Notification(), new Notification()));
        List<Notification> result = notificationService.getUserNotifications(100);
        assertEquals(2, result.size());
    }

    @Test
    void testMarkAsRead() {
        when(notificationDao.markAsRead(1)).thenReturn(true);
        boolean result = notificationService.markAsRead(1);
        assertTrue(result);
    }
}
