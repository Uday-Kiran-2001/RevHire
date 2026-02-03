package com.rev_hire.service;

import com.rev_hire.dao.ICompanyDao;
import com.rev_hire.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private ICompanyDao companyDao;

    private CompanyServiceImpl companyService;

    @BeforeEach
    void setUp() {
        companyService = new CompanyServiceImpl(companyDao);
    }

    @Test
    void testAddCompany() {
        Company com = new Company();
        com.setName("Tech Corp");
        when(companyDao.addCompany(any(Company.class))).thenReturn(true);

        boolean result = companyService.addCompany(com);
        assertTrue(result);
    }

    @Test
    void testGetCompanyById() {
        Company com = new Company();
        com.setCompanyId(50);
        com.setName("Tech Corp");
        when(companyDao.getCompanyById(50)).thenReturn(com);

        Company result = companyService.getCompanyById(50);
        assertNotNull(result);
        assertEquals("Tech Corp", result.getName());
    }

    @Test
    void testGetAllCompanies() {
        when(companyDao.getAllCompanies()).thenReturn(Arrays.asList(new Company(), new Company()));
        List<Company> result = companyService.getAllCompanies();
        assertEquals(2, result.size());
    }
}
