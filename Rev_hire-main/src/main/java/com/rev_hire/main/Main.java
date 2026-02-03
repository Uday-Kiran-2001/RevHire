package com.rev_hire.main;

import com.rev_hire.controller.UserAccountController;
import com.rev_hire.model.UserAccount;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserAccountController controller = new UserAccountController();

        while (true) {
            System.out.println("""
                    =================================
                         WELCOME TO REVHIRE
                    =================================
                    1. Login
                    2. Register
                    3. Exit
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
                case 1 -> loginFlow(sc, controller);
                case 2 -> registerFlow(sc, controller);
                case 3 -> {
                    System.out.println("👋 Thank you for using RevHire!");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid option. Try again.");
            }
        }
    }

    // ================= LOGIN FLOW =================
    private static void loginFlow(Scanner sc, UserAccountController controller) {

        System.out.println("\n==== LOGIN ====");

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Password: ");
        String password = sc.nextLine().trim();

        System.out.print("Role (JOB_SEEKER / EMPLOYER): ");
        String role = sc.nextLine().trim().toUpperCase();

        // VALIDATIONS
        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("❌ Email and Password cannot be empty");
            return;
        }

        if (!role.equals("JOB_SEEKER") && !role.equals("EMPLOYER")) {
            System.out.println("❌ Invalid role selected");
            return;
        }

        UserAccount user = controller.login(email, password, role);

        if (user == null) {
            System.out.println("❌ Invalid credentials");

            System.out.print("Forgot Password? (Y/N) or Register? (R): ");
            String choice = sc.nextLine().trim().toUpperCase();
            if (choice.equals("Y")) {
                forgotPasswordFlow(sc, controller);
            } else if (choice.equals("R")) {
                registerFlow(sc, controller);
            }
            return;
        }

        System.out.println("✅ Login successful!");

        // 🔁 ROLE BASED REDIRECTION
        if (user.getRole().equals("EMPLOYER")) {
            EmployerMain.start(sc, user.getId());
        } else {
            JobSeekerMain.start(sc, user.getId());
        }
    }

    // ================= REGISTER FLOW =================
    private static void registerFlow(Scanner sc, UserAccountController controller) {

        try {
            System.out.println("\n==== REGISTER ====");

            UserAccount user = new UserAccount();

            System.out.print("Email: ");
            String email = sc.nextLine().trim();

            System.out.print("Password: ");
            String password = sc.nextLine().trim();

            System.out.print("Confirm Password: ");
            String confirm = sc.nextLine().trim();

            System.out.print("Role (JOB_SEEKER / EMPLOYER): ");
            String role = sc.nextLine().trim().toUpperCase();

            // VALIDATIONS
            if (!com.rev_hire.util.ValidationUtil.isValidEmail(email)) {
                System.out.println("❌ Invalid email format");
                return;
            }

            if (!com.rev_hire.util.ValidationUtil.isValidPassword(password)) {
                System.out.println("❌ Password weak! Must have 8+ chars, Uppercase, Lowercase, Digit & Special char");
                return;
            }

            if (!password.equals(confirm)) {
                System.out.println("❌ Passwords do not match");
                return;
            }

            if (!role.equals("JOB_SEEKER") && !role.equals("EMPLOYER")) {
                System.out.println("❌ Invalid role selected");
                return;
            }

            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);

            boolean success = controller.register(user);

            if (!success) {
                System.out.println("❌ Registration failed (email may already exist)");
                return;
            }

            System.out.println("✅ Registration successful!");

            // AUTO CREATE ROLE ROW (already in your service)
            // Employer / JobSeeker row is auto created

            // AUTO LOGIN PROMPT
            System.out.print("Do you want to login now? (Y/N): ");
            if (sc.nextLine().equalsIgnoreCase("Y")) {
                loginFlow(sc, controller);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
