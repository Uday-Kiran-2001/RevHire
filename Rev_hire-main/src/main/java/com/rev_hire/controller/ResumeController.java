package com.rev_hire.controller;

import com.rev_hire.model.Resume;
import com.rev_hire.service.IResumeService;
import com.rev_hire.service.ResumeServiceImpl;

public class ResumeController {

    private static IResumeService service = new ResumeServiceImpl();

    public boolean addResume(Resume resume) {
        return service.addResume(resume);
    }

    public Resume getResume(int jobSeekerId) {
        return service.getResumeByJobSeeker(jobSeekerId);
    }

    public boolean updateResume(Resume resume) {
        return service.updateResume(resume);
    }

    public boolean deleteResume(int jobSeekerId) {
        return service.deleteResume(jobSeekerId);
    }
}
