package com.rev_hire.controller;

import com.rev_hire.model.Employer;
import com.rev_hire.service.EmployerServiceImpl;
import com.rev_hire.service.IEmployerService;

public class EmployerController {

    private IEmployerService service = new EmployerServiceImpl();

    public Employer getEmployerByUserId(int userId) {
        return service.getEmployerByUserId(userId);
    }

    public boolean createEmployer(int userId, int companyId) {
        return service.registerEmployer(userId, companyId);
    }
}
