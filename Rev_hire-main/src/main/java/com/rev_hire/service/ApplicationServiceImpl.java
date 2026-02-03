package com.rev_hire.service;

import com.rev_hire.dao.*;
import com.rev_hire.model.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ApplicationServiceImpl implements IApplicationService {

    private static final Logger logger = LogManager.getLogger(ApplicationServiceImpl.class);
    private IApplicationDao dao;

    public ApplicationServiceImpl() {
        this.dao = new ApplicationDaoImpl();
    }

    public ApplicationServiceImpl(IApplicationDao dao) {
        this.dao = dao;
    }

    public boolean applyJob(Application application) {
        logger.info("Applying for job: {} by user: {}", application.getJobId(), application.getJobSeekerId());
        return dao.applyJob(application);
    }

    public List<Application> getApplicationsByJobSeeker(int jobSeekerId) {
        logger.info("Fetching applications for jobSeekerId: {}", jobSeekerId);
        return dao.getApplicationsByJobSeeker(jobSeekerId);
    }

    public List<Application> getApplicationsByJob(int jobId) {
        logger.info("Fetching applications for jobId: {}", jobId);
        return dao.getApplicationsByJob(jobId);
    }

    public boolean updateStatus(int applicationId, String status) {
        logger.info("Updating application status. ID: {}, Status: {}", applicationId, status);
        return dao.updateStatus(applicationId, status);
    }

    public boolean withdrawApplication(int applicationId, String reason) {
        logger.info("Withdrawing application. ID: {}, Reason: {}", applicationId, reason);
        return dao.withdrawApplication(applicationId, reason);
    }
}
