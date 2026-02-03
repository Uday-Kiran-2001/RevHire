package com.rev_hire.main;

import com.rev_hire.controller.ApplicationController;
import com.rev_hire.model.Application;

import java.util.List;
import java.util.Scanner;

public class ApplicationEmployerMain {

    public static void start(Scanner sc) {

        // Scanner sc = new Scanner(System.in);
        ApplicationController controller = new ApplicationController();

        while (true) {
            System.out.println("""
                    ==== APPLICATION MENU (EMPLOYER) ====
                    1. View Applications by Job
                    2. Update Application Status
                    3. Back
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
                    System.out.print("Enter Job ID: ");
                    int jobId;
                    try {
                        jobId = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("❌ Invalid ID.");
                        continue;
                    }

                    List<Application> list = controller.getJobApplications(jobId);

                    for (Application a : list) {
                        System.out.println("AppID: " + a.getApplicationId()
                                + " | JobSeekerID: " + a.getJobSeekerId()
                                + " | Status: " + a.getStatus());
                    }
                }

                case 2 -> {
                    System.out.print("Application ID: ");
                    int appId;
                    try {
                        appId = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("❌ Invalid ID.");
                        continue;
                    }

                    System.out.print("New Status (SHORTLISTED/REJECTED/INTERVIEW): ");
                    String status = sc.nextLine();

                    System.out.println("Updated: " +
                            controller.updateStatus(appId, status));
                }

                case 3 -> {
                    return;
                }
            }
        }
    }
}
