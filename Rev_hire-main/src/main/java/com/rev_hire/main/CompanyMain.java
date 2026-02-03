package com.rev_hire.main;

import com.rev_hire.controller.CompanyController;
import com.rev_hire.model.Company;

import java.util.Scanner;

public class CompanyMain {

    public static void start(Scanner sc) {

        // Scanner sc = new Scanner(System.in);
        CompanyController controller = new CompanyController();

        while (true) {
            System.out.println("""
                    ==== COMPANY MENU ====
                    1. Create Company
                    2. View Company
                    3. View All Companies
                    4. Update Company
                    5. Delete Company
                    6. Back
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

                case 1 -> {
                    Company c = new Company();
                    System.out.print("Name: ");
                    c.setName(sc.nextLine());
                    System.out.print("Industry: ");
                    c.setIndustry(sc.nextLine());
                    System.out.print("Size: ");
                    c.setSize(sc.nextLine());
                    System.out.print("Description: ");
                    c.setDescription(sc.nextLine());
                    System.out.print("Website: ");
                    c.setWebsite(sc.nextLine());
                    System.out.print("Location: ");
                    c.setLocation(sc.nextLine());

                    System.out.println("Company Created: " +
                            controller.addCompany(c));
                }

                case 2 -> {
                    System.out.print("Company ID: ");
                    int id;
                    try {
                        id = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("❌ Invalid ID.");
                        continue;
                    }
                    Company c = controller.getCompany(id);
                    if (c != null) {
                        System.out.println("Name: " + c.getName());
                        System.out.println("Industry: " + c.getIndustry());
                        System.out.println("Location: " + c.getLocation());
                    } else {
                        System.out.println("Company not found");
                    }
                }

                case 3 -> controller.getAllCompanies()
                        .forEach(c -> System.out.println(
                                c.getCompanyId() + " - " + c.getName()));

                case 4 -> {
                    System.out.print("Company ID: ");
                    int id;
                    try {
                        id = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("❌ Invalid ID.");
                        continue;
                    }

                    Company c = controller.getCompany(id);
                    if (c != null) {
                        System.out.print("New Name: ");
                        c.setName(sc.nextLine());
                        System.out.print("New Location: ");
                        c.setLocation(sc.nextLine());
                        System.out.println("Updated: " +
                                controller.updateCompany(c));
                    }
                }

                case 5 -> {
                    System.out.print("Company ID: ");
                    int id;
                    try {
                        id = Integer.parseInt(sc.nextLine());
                        System.out.println("Deleted: " + controller.deleteCompany(id));
                    } catch (Exception e) {
                        System.out.println("❌ Invalid ID.");
                    }
                }

                case 6 -> {
                    return;
                }
            }
        }
    }
}
