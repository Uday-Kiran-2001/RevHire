package com.rev_hire.service;

import com.rev_hire.dao.IUserAccountDao;
import com.rev_hire.model.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {

    @Mock
    private IUserAccountDao userAccountDao;

    private UserAccountServiceImpl userAccountService;

    @BeforeEach
    void setUp() {
        userAccountService = new UserAccountServiceImpl(userAccountDao);
    }

    @Test
    void testAddUserAccount_Success() throws SQLException {
        UserAccount user = new UserAccount();
        user.setEmail("test@example.com");

        when(userAccountDao.emailExists("test@example.com")).thenReturn(false);
        when(userAccountDao.addUserAccount(any(UserAccount.class))).thenReturn(true);

        boolean result = userAccountService.addUserAccount(user);
        assertTrue(result);
        verify(userAccountDao, times(1)).addUserAccount(user);
    }

    @Test
    void testAddUserAccount_EmailExists() throws SQLException {
        UserAccount user = new UserAccount();
        user.setEmail("existing@example.com");

        when(userAccountDao.emailExists("existing@example.com")).thenReturn(true);

        boolean result = userAccountService.addUserAccount(user);
        assertFalse(result);
        verify(userAccountDao, never()).addUserAccount(any(UserAccount.class));
    }

    @Test
    void testLogin() {
        UserAccount user = new UserAccount();
        user.setEmail("user@example.com");
        when(userAccountDao.login("user@example.com", "password", "JOB_SEEKER")).thenReturn(user);

        UserAccount result = userAccountService.login("user@example.com", "password", "JOB_SEEKER");
        assertNotNull(result);
        assertEquals("user@example.com", result.getEmail());
    }
}
