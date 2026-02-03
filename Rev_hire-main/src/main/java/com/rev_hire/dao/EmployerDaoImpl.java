package com.rev_hire.dao;

import com.rev_hire.model.Employer;
import com.rev_hire.util.JDBCUtil;

import java.sql.*;

public class EmployerDaoImpl implements IEmployerDao {

    @Override
    public Employer getEmployerByUserId(int userId) {

        String sql = "SELECT * FROM employers WHERE user_id = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Employer e = new Employer();
                e.setEmployerId(rs.getInt("employer_id"));
                e.setUserId(rs.getInt("user_id"));
                e.setCompanyId(rs.getInt("company_id"));
                return e;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean createEmployer(int userId, int companyId) {

        String sql = "INSERT INTO employers (user_id, company_id) VALUES (?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, companyId);

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
