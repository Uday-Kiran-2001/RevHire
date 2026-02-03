package com.rev_hire.controller;

import com.rev_hire.model.Company;
import com.rev_hire.service.CompanyServiceImpl;
import com.rev_hire.service.ICompanyService;

import java.util.List;

public class CompanyController {

    private static ICompanyService service = new CompanyServiceImpl();

    public boolean addCompany(Company c) {
        return service.addCompany(c);
    }

    public Company getCompany(int id) {
        return service.getCompanyById(id);
    }

    public List<Company> getAllCompanies() {
        return service.getAllCompanies();
    }

    public boolean updateCompany(Company c) {
        return service.updateCompany(c);
    }

    public boolean deleteCompany(int id) {
        return service.deleteCompany(id);
    }
}
