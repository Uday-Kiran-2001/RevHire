package com.rev_hire.service

import com.rev_hire.dao.IJobDao;
import com.rev_hire.model.Job;
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
class JobServiceTest {

    @Mock
    private IJobDao jobDao;

    private JobServiceImpl jobService;

    @BeforeEach
    void setUp() {
        jobService = new JobServiceImpl(jobDao);
    }

    @Test
    void testAddJob() {
        Job job = new Job();
        job.setTitle("Software Engineer");
        when(jobDao.addJob(any(Job.class))).thenReturn(true);

        boolean result = jobService.addJob(job);
        assertTrue(result);
        verify(jobDao, times(1)).addJob(job);
    }

    @Test
    void testGetJobById() {
        Job job = new Job();
        job.setJobId(1);
        job.setTitle("Backend Dev");
        when(jobDao.getJobById(1)).thenReturn(job);

        Job result = jobService.getJobById(1);
        assertNotNull(result);
        assertEquals("Backend Dev", result.getTitle());
    }

    @Test
    void testGetAllJobs() {
        Job j1 = new Job();
        j1.setTitle("J1");
        Job j2 = new Job();
        j2.setTitle("J2");
        when(jobDao.getAllJobs()).thenReturn(Arrays.asList(j1, j2));

        List<Job> jobs = jobService.getAllJobs();
        assertEquals(2, jobs.size());
    }

    @Test
    void testDeleteJob() {
        when(jobDao.deleteJob(10)).thenReturn(true);
        boolean result = jobService.deleteJob(10);
        assertTrue(result);
    }
}
