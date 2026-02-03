package com.rev_hire.dao;

import com.rev_hire.model.Application;
import com.rev_hire.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDaoImpl implements IApplicationDao {

    @Override
    public boolean applyJob(Application a) {

        String sql = """
            INSERT INTO applications
            (job_id, job_seeker_id, resume_id, cover_letter, status, applied_date)
            VALUES (?, ?, ?, ?, 'APPLIED', SYSDATE)
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getJobId());
            ps.setInt(2, a.getJobSeekerId());
            ps.setInt(3, a.getResumeId());
            ps.setString(4, a.getCoverLetter());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Application> getApplicationsByJobSeeker(int jobSeekerId) {

        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE job_seeker_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobSeekerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapApplication(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Application> getApplicationsByJob(int jobId) {

        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE job_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapApplication(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateStatus(int applicationId, String status) {

        String sql = "UPDATE applications SET status=? WHERE application_id=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, applicationId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean withdrawApplication(int applicationId, String reason) {

        String sql = """
            UPDATE applications
            SET status='WITHDRAWN', withdraw_reason=?
            WHERE application_id=?
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, reason);
            ps.setInt(2, applicationId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Application mapApplication(ResultSet rs) throws SQLException {

        Application a = new Application();
        a.setApplicationId(rs.getInt("application_id"));
        a.setJobId(rs.getInt("job_id"));
        a.setJobSeekerId(rs.getInt("job_seeker_id"));
        a.setResumeId(rs.getInt("resume_id"));
        a.setCoverLetter(rs.getString("cover_letter"));
        a.setStatus(rs.getString("status"));
        a.setAppliedDate(rs.getDate("applied_date"));
        a.setWithdrawReason(rs.getString("withdraw_reason"));
        return a;
    }
}
