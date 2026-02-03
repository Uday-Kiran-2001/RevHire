package com.rev_hire.model;

public class Resume {

    private int resumeId;
    private int jobSeekerId;
    private String objective;
    private String education;
    private String experience;
    private String skills;
    private String projects;

    public int getResumeId() {
        return resumeId;
    }
    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public int getJobSeekerId() {
        return jobSeekerId;
    }
    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getObjective() {
        return objective;
    }
    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSkills() {
        return skills;
    }
    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getProjects() {
        return projects;
    }
    public void setProjects(String projects) {
        this.projects = projects;
    }
}
