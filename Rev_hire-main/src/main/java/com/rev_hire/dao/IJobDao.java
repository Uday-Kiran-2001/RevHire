package com.rev_hire.dao;

import com.rev_hire.model.Job;
import java.util.List;

public interface IJobDao {

    boolean addJob(Job job);
    Job getJobById(int jobId);
    List<Job> getJobsByCompany(int companyId);
    List<Job> getAllJobs();
    boolean updateJob(Job job);
    boolean deleteJob(int jobId);
}
