package com.rev_hire.model;

public class JobSeeker {

    private int jobSeekerId;
    private int userId;
    private String name;
    private String phone;
    private int experienceYears;
    private int profileCompletion;

    public int getJobSeekerId() {
        return jobSeekerId;
    }
    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getExperienceYears() {
        return experienceYears;
    }
    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public int getProfileCompletion() {
        return profileCompletion;
    }
    public void setProfileCompletion(int profileCompletion) {
        this.profileCompletion = profileCompletion;
    }
}
