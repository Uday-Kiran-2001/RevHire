package com.rev_hire.service;

import com.rev_hire.dao.CompanyDaoImpl;
import com.rev_hire.dao.ICompanyDao;
import com.rev_hire.model.Company;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CompanyServiceImpl implements ICompanyService {

    private static final Logger logger = LogManager.getLogger(CompanyServiceImpl.class);
    private ICompanyDao companyDao;

    public CompanyServiceImpl() {
        this.companyDao = new CompanyDaoImpl();
    }

    public CompanyServiceImpl(ICompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public boolean addCompany(Company c) {
        logger.info("Adding new company: {}", c.getName());
        return companyDao.addCompany(c);
    }

    public Company getCompanyById(int id) {
        logger.debug("Fetching company by ID: {}", id);
        return companyDao.getCompanyById(id);
    }

    public List<Company> getAllCompanies() {
        logger.info("Fetching all companies");
        return companyDao.getAllCompanies();
    }

    public boolean updateCompany(Company c) {
        logger.info("Updating company: {}", c.getName());
        return companyDao.updateCompany(c);
    }

    public boolean deleteCompany(int id) {
        logger.info("Deleting company ID: {}", id);
        return companyDao.deleteCompany(id);
    }
}
