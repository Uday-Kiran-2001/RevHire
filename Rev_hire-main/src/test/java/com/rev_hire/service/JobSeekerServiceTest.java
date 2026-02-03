package com.rev_hire.service;

import com.rev_hire.dao.JobSeekerDao;
import com.rev_hire.model.JobSeeker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobSeekerServiceTest {

    @Mock
    private JobSeekerDao jobSeekerDao;

    private JobSeekerServiceImpl jobSeekerService;

    @BeforeEach
    void setUp() {
        jobSeekerService = new JobSeekerServiceImpl(jobSeekerDao);
    }

    @Test
    void testGetJobSeekerByUserId() {
        JobSeeker js = new JobSeeker();
        js.setUserId(101);
        js.setName("Alice");
        when(jobSeekerDao.getJobSeekerByUserId(101)).thenReturn(js);

        JobSeeker result = jobSeekerService.getJobSeekerByUserId(101);
        assertNotNull(result);
        assertEquals("Alice", result.getName());
        verify(jobSeekerDao, times(1)).getJobSeekerByUserId(101);
    }

    @Test
    void testCreateJobSeeker() {
        JobSeeker js = new JobSeeker();
        js.setName("Bob");
        when(jobSeekerDao.createJobSeeker(any(JobSeeker.class))).thenReturn(true);

        boolean result = jobSeekerService.createJobSeeker(js);
        assertTrue(result);
        verify(jobSeekerDao, times(1)).createJobSeeker(js);
    }
}
