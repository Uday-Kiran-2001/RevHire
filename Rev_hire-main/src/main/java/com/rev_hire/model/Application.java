package com.rev_hire.model;

import java.sql.Date;

public class Application {

    private int applicationId;
    private int jobId;
    private int jobSeekerId;
    private int resumeId;
    private String coverLetter;
    private String status;
    private Date appliedDate;
    private String withdrawReason;

    public int getApplicationId() {
        return applicationId;
    }
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getJobId() {
        return jobId;
    }
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getJobSeekerId() {
        return jobSeekerId;
    }
    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public int getResumeId() {
        return resumeId;
    }
    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public String getCoverLetter() {
        return coverLetter;
    }
    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }
    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getWithdrawReason() {
        return withdrawReason;
    }
    public void setWithdrawReason(String withdrawReason) {
        this.withdrawReason = withdrawReason;
    }
}
