package com.rev_hire.main;

import com.rev_hire.controller.EmployerController;
import com.rev_hire.model.Employer;

import java.util.Scanner;

public class EmployerMain {

    public static void start(Scanner sc, int userId) {

        EmployerController controller = new EmployerController();
        // Scanner sc = new Scanner(System.in);

        Employer employer = controller.getEmployerByUserId(userId);

        // ================= NEW EMPLOYER =================
        if (employer == null) {
            System.out.println("👋 Welcome New Employer!");
            System.out.print("Enter Company ID to link: ");
            int companyId;
            try {
                companyId = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Invalid ID.");
                return;
            }

            boolean created = controller.createEmployer(userId, companyId);

            if (!created) {
                System.out.println("❌ Failed to create employer profile");
                return;
            }

            System.out.println("✅ Employer profile created successfully");

            // 🔥🔥 RE-FETCH EMPLOYER (THIS IS THE FIX)
            employer = controller.getEmployerByUserId(userId);
        }

        // ================= EMPLOYER MENU =================
        while (true) {
            System.out.println("""
                    ===== EMPLOYER MENU =====
                    1. Post New Job
                    2. View My Jobs
                    3. View Applicants
                    4. Update Application Status
                    5. Logout
                    """);

            System.out.print("Enter choice: ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Please enter a valid number");
                continue;
            }

            switch (choice) {
                case 1 -> JobMain.start(sc, employer.getCompanyId());
                case 2 -> JobMain.start(sc, employer.getCompanyId());
                case 3 -> ApplicationEmployerMain.start(sc);
                case 4 -> ApplicationEmployerMain.start(sc);
                case 5 -> {
                    System.out.println("👋 Logged out");
                    return;
                }
                default -> System.out.println("❌ Invalid option");
            }
        }
    }

}
