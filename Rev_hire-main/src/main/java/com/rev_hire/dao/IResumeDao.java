package com.rev_hire.dao;

import com.rev_hire.model.Resume;

public interface IResumeDao {

    boolean addResume(Resume resume);
    Resume getResumeByJobSeeker(int jobSeekerId);
    boolean updateResume(Resume resume);
    boolean deleteResume(int jobSeekerId);
}
