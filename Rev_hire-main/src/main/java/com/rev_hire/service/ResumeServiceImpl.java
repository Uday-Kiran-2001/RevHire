package com.rev_hire.service;

import com.rev_hire.dao.IResumeDao;
import com.rev_hire.dao.ResumeDaoImpl;
import com.rev_hire.model.Resume;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResumeServiceImpl implements IResumeService {

    private static final Logger logger = LogManager.getLogger(ResumeServiceImpl.class);
    private IResumeDao resumeDao;

    public ResumeServiceImpl() {
        this.resumeDao = new ResumeDaoImpl();
    }

    public ResumeServiceImpl(IResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    public boolean addResume(Resume resume) {
        logger.info("Adding resume for jobSeekerId: {}", resume.getJobSeekerId());
        return resumeDao.addResume(resume);
    }

    public Resume getResumeByJobSeeker(int jobSeekerId) {
        return resumeDao.getResumeByJobSeeker(jobSeekerId);
    }

    public boolean updateResume(Resume resume) {
        return resumeDao.updateResume(resume);
    }

    public boolean deleteResume(int jobSeekerId) {
        return resumeDao.deleteResume(jobSeekerId);
    }
}
