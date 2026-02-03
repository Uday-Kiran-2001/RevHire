package com.rev_hire.service;

import com.rev_hire.dao.IResumeDao;
import com.rev_hire.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResumeServiceTest {

    @Mock
    private IResumeDao resumeDao;

    private ResumeServiceImpl resumeService;

    @BeforeEach
    void setUp() {
        resumeService = new ResumeServiceImpl(resumeDao);
    }

    @Test
    void testAddResume() {
        Resume res = new Resume();
        res.setJobSeekerId(101);
        when(resumeDao.addResume(any(Resume.class))).thenReturn(true);

        boolean result = resumeService.addResume(res);
        assertTrue(result);
    }

    @Test
    void testGetResumeByJobSeeker() {
        Resume res = new Resume();
        res.setResumeId(1);
        when(resumeDao.getResumeByJobSeeker(101)).thenReturn(res);

        Resume result = resumeService.getResumeByJobSeeker(101);
        assertNotNull(result);
    }
}
