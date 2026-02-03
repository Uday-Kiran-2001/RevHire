package com.rev_hire.dao;

import com.rev_hire.model.JobSeeker;
import com.rev_hire.util.JDBCUtil;

import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobSeekerDaoImpl implements JobSeekerDao {

    private static final Logger logger = LogManager.getLogger(JobSeekerDaoImpl.class);

    @Override
    public JobSeeker getJobSeekerByUserId(int userId) {

        String sql = "SELECT * FROM job_seekers WHERE user_id = ?";

        try (Connection con = JDBCUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JobSeeker js = new JobSeeker();
                js.setJobSeekerId(rs.getInt("job_seeker_id"));
                js.setUserId(rs.getInt("user_id"));
                js.setName(rs.getString("name"));
                js.setPhone(rs.getString("phone"));
                js.setExperienceYears(rs.getInt("experience_years"));
                js.setProfileCompletion(rs.getInt("profile_completion"));
                return js;
            }

        } catch (Exception e) {
            logger.error("Error in getJobSeekerByUserId", e);
        }
        return null;
    }

    @Override
    public boolean createJobSeeker(JobSeeker js) {

        String sql = """
                    INSERT INTO job_seekers
                    (user_id, name, phone, experience_years, profile_completion)
                    VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection con = JDBCUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, js.getUserId());
            ps.setString(2, js.getName());
            ps.setString(3, js.getPhone());
            ps.setInt(4, js.getExperienceYears());
            ps.setInt(5, js.getProfileCompletion());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            logger.error("Error in createJobSeeker", e);
        }
        return false;
    }

    // ✅ MISSING METHOD — NOW FIXED
    @Override
    public int getResumeId(int jobSeekerId) {

        String sql = "SELECT resume_id FROM resumes WHERE job_seeker_id = ?";

        try (Connection con = JDBCUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobSeekerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("resume_id");
            }

        } catch (Exception e) {
            logger.error("Error in getResumeId", e);
        }
        return -1; // resume not found
    }
}
