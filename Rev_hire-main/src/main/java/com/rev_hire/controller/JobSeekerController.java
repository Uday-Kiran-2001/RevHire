package com.rev_hire.controller;

import com.rev_hire.model.JobSeeker;
import com.rev_hire.service.JobSeekerService;
import com.rev_hire.service.JobSeekerServiceImpl;

public class JobSeekerController {

    private final JobSeekerService service = new JobSeekerServiceImpl();

    public JobSeeker getJobSeekerByUserId(int userId) {
        return service.getJobSeekerByUserId(userId);
    }

    public boolean createJobSeeker(JobSeeker js) {
        return service.createJobSeeker(js);
    }

    public void viewProfile(int userId) {
        JobSeeker js = service.getJobSeekerByUserId(userId);

        if (js == null) {
            System.out.println("❌ Profile not found");
            return;
        }

        System.out.println("""
            ===== MY PROFILE =====
            Name       : %s
            Phone      : %s
            Experience : %d years
            Completion : %d%%
            """.formatted(
                js.getName(),
                js.getPhone(),
                js.getExperienceYears(),
                js.getProfileCompletion()
        ));
    }

    public int getResumeId(int jobSeekerId) {
        return service.getResumeId(jobSeekerId);
    }
}
