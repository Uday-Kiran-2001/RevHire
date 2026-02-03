package com.rev_hire.service;

import com.rev_hire.model.JobSeeker;

public interface JobSeekerService {

    JobSeeker getJobSeekerByUserId(int userId);

    boolean createJobSeeker(JobSeeker js);

    int getResumeId(int jobSeekerId);
}
