package com.rev_hire.service;

import com.rev_hire.model.UserAccount;

import java.sql.SQLException;

public interface IUserAccountService {
    // public boolean addUserAccount(UserAccount userAccount) throws SQLException;
    // public boolean updateUserAccount(UserAccount userAccount);
    // public boolean deleteUserAccount(int id);
    // public UserAccount getUserAccount(int id);
    // public List<UserAccount> getAllUserAccounts();
    // boolean isEmailExists(String email);

    boolean addUserAccount(UserAccount userAccount) throws SQLException;

    UserAccount login(String email, String password, String role);

    boolean resetPasswordByEmail(String email, String newPassword);

    boolean emailExists(String email);
}
