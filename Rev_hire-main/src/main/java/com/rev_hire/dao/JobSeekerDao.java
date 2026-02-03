package com.rev_hire.dao;

import com.rev_hire.model.JobSeeker;

public interface JobSeekerDao {

    JobSeeker getJobSeekerByUserId(int userId);

    boolean createJobSeeker(JobSeeker js);

    int getResumeId(int jobSeekerId);
}
