package com.rev_hire.service;

import com.rev_hire.model.Employer;

public interface IEmployerService {

    Employer getEmployerByUserId(int userId);

    boolean registerEmployer(int userId, int companyId);
}
