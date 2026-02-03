package com.rev_hire.dao;

import com.rev_hire.model.Job;
import com.rev_hire.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements IJobDao {

    @Override
    public boolean addJob(Job j) {

        String sql = """
            INSERT INTO jobs
            (company_id, title, description, skills_required,
             experience_required, education_required, location,
             salary_range, job_type, deadline, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, j.getCompanyId());
            ps.setString(2, j.getTitle());
            ps.setString(3, j.getDescription());
            ps.setString(4, j.getSkillsRequired());
            ps.setInt(5, j.getExperienceRequired());
            ps.setString(6, j.getEducationRequired());
            ps.setString(7, j.getLocation());
            ps.setString(8, j.getSalaryRange());
            ps.setString(9, j.getJobType());
            ps.setDate(10, j.getDeadline());
            ps.setString(11, j.getStatus());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Job getJobById(int id) {

        String sql = "SELECT * FROM jobs WHERE job_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapJob(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Job> getJobsByCompany(int companyId) {

        List<Job> list = new ArrayList<>();
        String sql = "SELECT * FROM jobs WHERE company_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapJob(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Job> getAllJobs() {

        List<Job> list = new ArrayList<>();
        String sql = "SELECT * FROM jobs";

        try (Connection con = JDBCUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapJob(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateJob(Job j) {

        String sql = """
            UPDATE jobs SET
            title=?, description=?, skills_required=?, experience_required=?,
            education_required=?, location=?, salary_range=?, job_type=?,
            deadline=?, status=?
            WHERE job_id=?
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, j.getTitle());
            ps.setString(2, j.getDescription());
            ps.setString(3, j.getSkillsRequired());
            ps.setInt(4, j.getExperienceRequired());
            ps.setString(5, j.getEducationRequired());
            ps.setString(6, j.getLocation());
            ps.setString(7, j.getSalaryRange());
            ps.setString(8, j.getJobType());
            ps.setDate(9, j.getDeadline());
            ps.setString(10, j.getStatus());
            ps.setInt(11, j.getJobId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteJob(int id) {

        String sql = "DELETE FROM jobs WHERE job_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Job mapJob(ResultSet rs) throws SQLException {

        Job j = new Job();
        j.setJobId(rs.getInt("job_id"));
        j.setCompanyId(rs.getInt("company_id"));
        j.setTitle(rs.getString("title"));
        j.setDescription(rs.getString("description"));
        j.setSkillsRequired(rs.getString("skills_required"));
        j.setExperienceRequired(rs.getInt("experience_required"));
        j.setEducationRequired(rs.getString("education_required"));
        j.setLocation(rs.getString("location"));
        j.setSalaryRange(rs.getString("salary_range"));
        j.setJobType(rs.getString("job_type"));
        j.setDeadline(rs.getDate("deadline"));
        j.setStatus(rs.getString("status"));
        return j;
    }
}
