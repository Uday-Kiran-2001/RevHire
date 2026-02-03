package com.rev_hire.dao;

import com.rev_hire.model.Resume;
import com.rev_hire.util.JDBCUtil;

import java.sql.*;

public class ResumeDaoImpl implements IResumeDao {

    @Override
    public boolean addResume(Resume r) {

        String sql = """
            INSERT INTO resumes
            (job_seeker_id, objective, education, experience, skills, projects)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getJobSeekerId());
            ps.setString(2, r.getObjective());
            ps.setString(3, r.getEducation());
            ps.setString(4, r.getExperience());
            ps.setString(5, r.getSkills());
            ps.setString(6, r.getProjects());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Resume getResumeByJobSeeker(int jobSeekerId) {

        String sql = "SELECT * FROM resumes WHERE job_seeker_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobSeekerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResume(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateResume(Resume r) {

        String sql = """
            UPDATE resumes SET
            objective=?, education=?, experience=?, skills=?, projects=?
            WHERE job_seeker_id=?
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getObjective());
            ps.setString(2, r.getEducation());
            ps.setString(3, r.getExperience());
            ps.setString(4, r.getSkills());
            ps.setString(5, r.getProjects());
            ps.setInt(6, r.getJobSeekerId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteResume(int jobSeekerId) {

        String sql = "DELETE FROM resumes WHERE job_seeker_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobSeekerId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Resume mapResume(ResultSet rs) throws SQLException {

        Resume r = new Resume();
        r.setResumeId(rs.getInt("resume_id"));
        r.setJobSeekerId(rs.getInt("job_seeker_id"));
        r.setObjective(rs.getString("objective"));
        r.setEducation(rs.getString("education"));
        r.setExperience(rs.getString("experience"));
        r.setSkills(rs.getString("skills"));
        r.setProjects(rs.getString("projects"));
        return r;
    }
}
