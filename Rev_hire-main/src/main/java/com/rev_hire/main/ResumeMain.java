package com.rev_hire.main;

import com.rev_hire.controller.ResumeController;
import com.rev_hire.model.Resume;

import java.util.Scanner;

public class ResumeMain {

    public static void start(Scanner sc, int jobSeekerId) {

        // Scanner sc = new Scanner(System.in);
        ResumeController controller = new ResumeController();

        while (true) {
            System.out.println("""
                    ==== RESUME MENU ====
                    1. Create Resume
                    2. View Resume
                    3. Update Resume
                    4. Delete Resume
                    5. Back
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
                    Resume r = new Resume();
                    r.setJobSeekerId(jobSeekerId);

                    System.out.print("Objective: ");
                    r.setObjective(sc.nextLine());
                    System.out.print("Education: ");
                    r.setEducation(sc.nextLine());
                    System.out.print("Experience: ");
                    r.setExperience(sc.nextLine());
                    System.out.print("Skills: ");
                    r.setSkills(sc.nextLine());
                    System.out.print("Projects: ");
                    r.setProjects(sc.nextLine());

                    System.out.println("Resume Created: " +
                            controller.addResume(r));
                }

                case 2 -> {
                    Resume r = controller.getResume(jobSeekerId);
                    if (r != null) {
                        System.out.println("Objective: " + r.getObjective());
                        System.out.println("Education: " + r.getEducation());
                        System.out.println("Experience: " + r.getExperience());
                        System.out.println("Skills: " + r.getSkills());
                        System.out.println("Projects: " + r.getProjects());
                    } else {
                        System.out.println("No resume found");
                    }
                }

                case 3 -> {
                    Resume r = controller.getResume(jobSeekerId);
                    if (r != null) {
                        System.out.print("New Objective: ");
                        r.setObjective(sc.nextLine());
                        System.out.print("New Skills: ");
                        r.setSkills(sc.nextLine());
                        System.out.println("Updated: " +
                                controller.updateResume(r));
                    }
                }

                case 4 -> {
                    System.out.println("Deleted: " +
                            controller.deleteResume(jobSeekerId));
                }

                case 5 -> {
                    return;
                }
            }
        }
    }
}
