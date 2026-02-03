package com.rev_hire.service;

import com.rev_hire.dao.EmployerDaoImpl;
import com.rev_hire.dao.IEmployerDao;
import com.rev_hire.model.Employer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployerServiceImpl implements IEmployerService {

    private static final Logger logger = LogManager.getLogger(EmployerServiceImpl.class);
    private IEmployerDao employerDao;

    public EmployerServiceImpl() {
        this.employerDao = new EmployerDaoImpl();
    }

    public EmployerServiceImpl(IEmployerDao employerDao) {
        this.employerDao = employerDao;
    }

    @Override
    public Employer getEmployerByUserId(int userId) {
        logger.info("Fetching employer details for userId: {}", userId);
        return employerDao.getEmployerByUserId(userId);
    }

    @Override
    public boolean registerEmployer(int userId, int companyId) {
        logger.info("Registering employer with userId: {} and companyId: {}", userId, companyId);
        return employerDao.createEmployer(userId, companyId);
    }
}
