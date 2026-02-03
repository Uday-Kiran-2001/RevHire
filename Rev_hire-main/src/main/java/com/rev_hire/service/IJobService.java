package com.rev_hire.service;

import com.rev_hire.model.Job;
import java.util.List;

public interface IJobService {

    boolean addJob(Job job);
    Job getJobById(int jobId);
    List<Job> getJobsByCompany(int companyId);
    List<Job> getAllJobs();
    boolean updateJob(Job job);
    boolean deleteJob(int jobId);

    void searchJobs();
}
