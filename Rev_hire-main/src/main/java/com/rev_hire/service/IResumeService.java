package com.rev_hire.service;

import com.rev_hire.model.Resume;

public interface IResumeService {

    boolean addResume(Resume resume);
    Resume getResumeByJobSeeker(int jobSeekerId);
    boolean updateResume(Resume resume);
    boolean deleteResume(int jobSeekerId);
}
