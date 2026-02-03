package com.rev_hire.service;

import com.rev_hire.dao.JobSeekerDao;
import com.rev_hire.dao.JobSeekerDaoImpl;
import com.rev_hire.model.JobSeeker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobSeekerServiceImpl implements JobSeekerService {

    private static final Logger logger = LogManager.getLogger(JobSeekerServiceImpl.class);
    private JobSeekerDao dao;

    public JobSeekerServiceImpl() {
        this.dao = new JobSeekerDaoImpl();
    }

    public JobSeekerServiceImpl(JobSeekerDao dao) {
        this.dao = dao;
    }

    @Override
    public JobSeeker getJobSeekerByUserId(int userId) {
        logger.info("Fetching JobSeeker details for userId: {}", userId);
        return dao.getJobSeekerByUserId(userId);
    }

    @Override
    public boolean createJobSeeker(JobSeeker js) {
        logger.info("Creating new JobSeeker profile for user: {}", js.getUserId());
        return dao.createJobSeeker(js);
    }

    @Override
    public int getResumeId(int jobSeekerId) {
        logger.debug("Retrieving resumeId for jobSeekerId: {}", jobSeekerId);
        return dao.getResumeId(jobSeekerId);
    }
}
