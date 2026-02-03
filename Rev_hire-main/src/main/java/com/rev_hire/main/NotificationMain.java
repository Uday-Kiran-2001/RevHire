package com.rev_hire.main;

import com.rev_hire.controller.NotificationController;
import com.rev_hire.model.Notification;

import java.util.List;
import java.util.Scanner;

public class NotificationMain {

    public static void start(Scanner sc, int userId) {

        // Scanner sc = new Scanner(System.in);
        NotificationController controller = new NotificationController();

        while (true) {
            System.out.println("""
                    ==== NOTIFICATIONS MENU ====
                    1. View Notifications
                    2. Mark Notification as Read
                    3. Delete Notification
                    4. Back
                    """);

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Please enter a valid number");
                continue;
            }

            switch (choice) {

                case 1 -> {
                    List<Notification> list = controller.getMyNotifications(userId);

                    if (list.isEmpty()) {
                        System.out.println("No notifications");
                    }

                    for (Notification n : list) {
                        System.out.println(
                                "ID: " + n.getNotificationId() +
                                        " | " + (n.getIsRead() == 0 ? "[UNREAD]" : "[READ]") +
                                        " | " + n.getMessage() +
                                        " | " + n.getCreatedAt());
                    }
                }

                case 2 -> {
                    System.out.print("Enter Notification ID: ");
                    int id;
                    try {
                        id = Integer.parseInt(sc.nextLine());
                        System.out.println("Marked as read: " + controller.markAsRead(id));
                    } catch (Exception e) {
                        System.out.println("❌ Invalid ID.");
                    }
                }

                case 3 -> {
                    System.out.print("Enter Notification ID: ");
                    int id;
                    try {
                        id = Integer.parseInt(sc.nextLine());
                        System.out.println("Deleted: " + controller.delete(id));
                    } catch (Exception e) {
                        System.out.println("❌ Invalid ID.");
                    }
                }

                case 4 -> {
                    return;
                }
            }
        }
    }
}
