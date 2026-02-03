package com.rev_hire.controller;

import com.rev_hire.model.Job;
import com.rev_hire.service.IJobService;
import com.rev_hire.service.JobServiceImpl;

import java.util.List;

public class JobController {

    private static IJobService service = new JobServiceImpl();

    public boolean addJob(Job job) {
        return service.addJob(job);
    }

    public Job getJob(int id) {
        return service.getJobById(id);
    }

    public List<Job> getJobsByCompany(int companyId) {
        return service.getJobsByCompany(companyId);
    }

    public List<Job> getAllJobs() {
        return service.getAllJobs();
    }

    public boolean updateJob(Job job) {
        return service.updateJob(job);
    }

    public boolean deleteJob(int id) {
        return service.deleteJob(id);
    }

    public static void searchJobs() {
        service.searchJobs();
    }
}
