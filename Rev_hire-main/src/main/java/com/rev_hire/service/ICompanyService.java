package com.rev_hire.service;

import com.rev_hire.model.Company;
import java.util.List;

public interface ICompanyService {

    boolean addCompany(Company company);
    Company getCompanyById(int companyId);
    List<Company> getAllCompanies();
    boolean updateCompany(Company company);
    boolean deleteCompany(int companyId);
}
