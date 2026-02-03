package com.rev_hire.dao;

import com.rev_hire.model.Company;
import com.rev_hire.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements ICompanyDao {

    @Override
    public boolean addCompany(Company c) {

        String sql = """
            INSERT INTO companies
            (name, industry, size, description, website, location)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getIndustry());
            ps.setString(3, c.getSize());
            ps.setString(4, c.getDescription());
            ps.setString(5, c.getWebsite());
            ps.setString(6, c.getLocation());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Company getCompanyById(int id) {

        String sql = "SELECT * FROM companies WHERE company_id = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapCompany(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Company> getAllCompanies() {

        List<Company> list = new ArrayList<>();
        String sql = "SELECT * FROM companies";

        try (Connection con = JDBCUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapCompany(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateCompany(Company c) {

        String sql = """
            UPDATE companies SET
            name=?, industry=?, size=?, description=?, website=?, location=?
            WHERE company_id=?
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getIndustry());
            ps.setString(3, c.getSize());
            ps.setString(4, c.getDescription());
            ps.setString(5, c.getWebsite());
            ps.setString(6, c.getLocation());
            ps.setInt(7, c.getCompanyId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCompany(int id) {

        String sql = "DELETE FROM companies WHERE company_id = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Company mapCompany(ResultSet rs) throws SQLException {

        Company c = new Company();
        c.setCompanyId(rs.getInt("company_id"));
        c.setName(rs.getString("name"));
        c.setIndustry(rs.getString("industry"));
        c.setSize(rs.getString("size"));
        c.setDescription(rs.getString("description"));
        c.setWebsite(rs.getString("website"));
        c.setLocation(rs.getString("location"));
        return c;
    }
}
