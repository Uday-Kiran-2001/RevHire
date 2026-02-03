package com.rev_hire.service;

import com.rev_hire.model.Application;
import java.util.List;

public interface IApplicationService {

    boolean applyJob(Application application);
    List<Application> getApplicationsByJobSeeker(int jobSeekerId);
    List<Application> getApplicationsByJob(int jobId);
    boolean updateStatus(int applicationId, String status);
    boolean withdrawApplication(int applicationId, String reason);
}
