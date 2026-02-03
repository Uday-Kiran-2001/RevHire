package com.rev_hire.dao;

import com.rev_hire.model.Company;
import java.util.List;

public interface ICompanyDao {

    boolean addCompany(Company company);
    Company getCompanyById(int companyId);
    List<Company> getAllCompanies();
    boolean updateCompany(Company company);
    boolean deleteCompany(int companyId);
}
