package com.rev_hire.controller;

import com.rev_hire.model.Application;
import com.rev_hire.service.*;

import java.util.List;

public class ApplicationController {

    private static IApplicationService service = new ApplicationServiceImpl();

    public boolean applyJob(Application application) {
        return service.applyJob(application);
    }

    public List<Application> getMyApplications(int jobSeekerId) {
        return service.getApplicationsByJobSeeker(jobSeekerId);
    }

    public List<Application> getJobApplications(int jobId) {
        return service.getApplicationsByJob(jobId);
    }

    public boolean updateStatus(int applicationId, String status) {
        return service.updateStatus(applicationId, status);
    }

    public boolean withdrawApplication(int applicationId, String reason) {
        return service.withdrawApplication(applicationId, reason);
    }
}
