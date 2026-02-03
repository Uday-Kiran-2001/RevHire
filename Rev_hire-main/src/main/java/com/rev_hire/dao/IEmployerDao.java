package com.rev_hire.dao;

import com.rev_hire.model.Employer;

public interface IEmployerDao {

    Employer getEmployerByUserId(int userId);

    boolean createEmployer(int userId, int companyId);
}
