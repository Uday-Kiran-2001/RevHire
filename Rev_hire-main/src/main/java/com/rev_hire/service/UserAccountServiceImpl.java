package com.rev_hire.service;

import com.rev_hire.dao.IUserAccountDao;
import com.rev_hire.dao.UserAccountDaoImpl;
import com.rev_hire.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class UserAccountServiceImpl implements IUserAccountService {

    private static final Logger logger = LogManager.getLogger(UserAccountServiceImpl.class);
    private IUserAccountDao userAccountDao;

    public UserAccountServiceImpl() {
        this.userAccountDao = new UserAccountDaoImpl();
    }

    public UserAccountServiceImpl(IUserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }
    // @Override
    // public boolean addUserAccount(UserAccount userAccount) throws SQLException {
    //
    // if (userAccountDao.isEmailExists(userAccount.getEmail())) {
    // System.out.println("Email already exists. Registration failed.");
    // return false;
    // }
    //
    // return userAccountDao.addUserAccount(userAccount);
    // }
    //
    // @Override
    // public boolean updateUserAccount(UserAccount userAccount) {
    //
    // return userAccountDao.updateUserAccount(userAccount);
    // }
    //
    // @Override
    // public boolean deleteUserAccount(int id) {
    //
    // return userAccountDao.deleteUserAccount(id);
    // }
    //
    // @Override
    // public UserAccount getUserAccount(int id) {
    //
    // return userAccountDao.getUserAccount(id);
    // }
    //
    // @Override
    // public List<UserAccount> getAllUserAccounts() {
    // return List.of();
    // }
    //
    // @Override
    // public boolean isEmailExists(String email) {
    // return userAccountDao.isEmailExists(email);
    // }

    @Override
    public boolean addUserAccount(UserAccount userAccount) throws SQLException {
        logger.info("Attempting to register user: {}", userAccount.getEmail());

        if (userAccountDao.emailExists(userAccount.getEmail())) {
            logger.warn("Email already exists: {}", userAccount.getEmail());
            return false;
        }

        // Hash the password before saving
        String originalPassword = userAccount.getPassword();
        userAccount.setPassword(com.rev_hire.util.PasswordUtil.hashPassword(originalPassword));

        return userAccountDao.addUserAccount(userAccount);
    }

    @Override
    public UserAccount login(String email, String password, String role) {
        logger.info("Login attempt for email: {}, role: {}", email, role);

        // In a real flow, we would fetch the user by email first, then check hashed
        // password
        // But for now, we'll hash the input password and compare in DAO
        String hashedPassword = com.rev_hire.util.PasswordUtil.hashPassword(password);
        return userAccountDao.login(email, hashedPassword, role);
    }

    @Override
    public boolean resetPasswordByEmail(String email, String newPassword) {
        logger.info("Resetting password for email: {}", email);
        String hashedPassword = com.rev_hire.util.PasswordUtil.hashPassword(newPassword);
        return userAccountDao.updatePasswordByEmail(email, hashedPassword);
    }

    @Override
    public boolean emailExists(String email) {
        return userAccountDao.emailExists(email);
    }
}
