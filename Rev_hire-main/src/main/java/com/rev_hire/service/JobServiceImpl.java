package com.rev_hire.service;

import com.rev_hire.dao.IJobDao;
import com.rev_hire.dao.JobDaoImpl;
import com.rev_hire.model.Job;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobServiceImpl implements IJobService {

    private static final Logger logger = LogManager.getLogger(JobServiceImpl.class);
    private IJobDao jobDao;

    public JobServiceImpl() {
        this.jobDao = new JobDaoImpl();
    }

    public JobServiceImpl(IJobDao jobDao) {
        this.jobDao = jobDao;
    }

    public boolean addJob(Job job) {
        logger.info("Adding new job: {}", job.getTitle());
        return jobDao.addJob(job);
    }

    public Job getJobById(int jobId) {
        logger.info("Fetching job by ID: {}", jobId);
        return jobDao.getJobById(jobId);
    }

    public List<Job> getJobsByCompany(int companyId) {
        logger.info("Fetching jobs for companyId: {}", companyId);
        return jobDao.getJobsByCompany(companyId);
    }

    public List<Job> getAllJobs() {
        logger.info("Fetching all jobs");
        return jobDao.getAllJobs();
    }

    public boolean updateJob(Job job) {
        return jobDao.updateJob(job);
    }

    public boolean deleteJob(int jobId) {
        logger.info("Deleting job: {}", jobId);
        return jobDao.deleteJob(jobId);
    }

    public void searchJobs() {
        jobDao.getAllJobs();
    }
}
