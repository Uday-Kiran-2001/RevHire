package com.rev_hire.service;

import com.rev_hire.dao.IApplicationDao;
import com.rev_hire.model.Application;
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
class ApplicationServiceTest {

    @Mock
    private IApplicationDao applicationDao;

    private ApplicationServiceImpl applicationService;

    @BeforeEach
    void setUp() {
        applicationService = new ApplicationServiceImpl(applicationDao);
    }

    @Test
    void testApplyJob() {
        Application app = new Application();
        app.setJobId(1);
        when(applicationDao.applyJob(any(Application.class))).thenReturn(true);

        boolean result = applicationService.applyJob(app);
        assertTrue(result);
        verify(applicationDao, times(1)).applyJob(app);
    }

    @Test
    void testGetApplicationsByJobSeeker() {
        Application app1 = new Application();
        Application app2 = new Application();
        when(applicationDao.getApplicationsByJobSeeker(101)).thenReturn(Arrays.asList(app1, app2));

        List<Application> result = applicationService.getApplicationsByJobSeeker(101);
        assertEquals(2, result.size());
    }

    @Test
    void testUpdateStatus() {
        when(applicationDao.updateStatus(1, "Reviewed")).thenReturn(true);
        boolean result = applicationService.updateStatus(1, "Reviewed");
        assertTrue(result);
    }
}
