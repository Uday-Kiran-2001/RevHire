package com.rev_hire.service;

import com.rev_hire.dao.IEmployerDao;
import com.rev_hire.model.Employer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployerServiceTest {

    @Mock
    private IEmployerDao employerDao;

    private EmployerServiceImpl employerService;

    @BeforeEach
    void setUp() {
        employerService = new EmployerServiceImpl(employerDao);
    }

    @Test
    void testGetEmployerByUserId() {
        Employer emp = new Employer();
        emp.setUserId(10);
        when(employerDao.getEmployerByUserId(10)).thenReturn(emp);

        Employer result = employerService.getEmployerByUserId(10);
        assertNotNull(result);
        assertEquals(10, result.getUserId());
    }

    @Test
    void testRegisterEmployer() {
        when(employerDao.createEmployer(10, 5)).thenReturn(true);
        boolean result = employerService.registerEmployer(10, 5);
        assertTrue(result);
        verify(employerDao, times(1)).createEmployer(10, 5);
    }
}
