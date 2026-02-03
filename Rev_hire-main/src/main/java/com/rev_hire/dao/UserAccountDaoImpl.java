package com.rev_hire.dao;

import com.rev_hire.model.UserAccount;
import com.rev_hire.util.JDBCUtil;

import java.sql.*;

public class UserAccountDaoImpl implements IUserAccountDao {

    // @Override
    // public boolean addUserAccount(UserAccount userAccount) throws SQLException {
    // String sql = "INSERT INTO users(user_id, email, password, role) VALUES (?, ?,
    // ?, ?)";
    // try (Connection con = JDBCUtil.getConnection();
    // PreparedStatement ps = con.prepareStatement(sql)){
    //
    // ps.setInt(1, userAccount.getId());
    // ps.setString(2, userAccount.getEmail());
    // ps.setString(3, userAccount.getPassword());
    // ps.setString(4, userAccount.getRole());
    //
    // return ps.executeUpdate() > 0;
    //
    // } catch (Exception e){
    // e.printStackTrace();
    // }
    // return false;
    // }
    //
    // @Override
    // public boolean updateUserAccount(UserAccount userAccount) {
    // String sql = "UPDATE users SET email=?, password=?, role=? WHERE user_id=?";
    // try (Connection con = JDBCUtil.getConnection();
    // PreparedStatement ps = con.prepareStatement(sql)) {
    //
    // ps.setString(1, userAccount.getEmail());
    // ps.setString(2, userAccount.getPassword());
    // ps.setString(3, userAccount.getRole());
    // ps.setInt(4, userAccount.getId());
    //
    // return ps.executeUpdate() > 0;
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return false;
    // }
    //
    // @Override
    // public boolean deleteUserAccount(int id) {
    // String sql = "DELETE FROM users WHERE user_id=?";
    // try (Connection con = JDBCUtil.getConnection();
    // PreparedStatement ps = con.prepareStatement(sql)) {
    //
    // ps.setInt(1, id);
    // return ps.executeUpdate() > 0;
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return false;
    // }
    //
    // @Override
    // public UserAccount getUserAccount(int id) {
    // String sql = "SELECT * FROM users WHERE user_id=?";
    // try (Connection con = JDBCUtil.getConnection();
    // PreparedStatement ps = con.prepareStatement(sql)) {
    //
    // ps.setInt(1, id);
    // ResultSet rs = ps.executeQuery();
    //
    // if (rs.next()) {
    // UserAccount u = new UserAccount();
    // u.setId(rs.getInt("user_id"));
    // u.setEmail(rs.getString("email"));
    // u.setPassword(rs.getString("password"));
    // u.setRole(rs.getString("role"));
    // return u;
    // }
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return null;
    // }
    //
    // @Override
    // public List<UserAccount> getAllUserAccounts() {
    // List<UserAccount> list = new ArrayList<>();
    // String sql = "SELECT * FROM users";
    //
    // try (Connection con = JDBCUtil.getConnection();
    // Statement st = con.createStatement();
    // ResultSet rs = st.executeQuery(sql)) {
    //
    // while (rs.next()) {
    // UserAccount u = new UserAccount();
    // u.setId(rs.getInt("user_id"));
    // u.setEmail(rs.getString("email"));
    // u.setPassword(rs.getString("password"));
    // u.setRole(rs.getString("role"));
    // list.add(u);
    // }
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return list;
    // }
    //
    // @Override
    // public boolean isEmailExists(String email) {
    // String sql = "SELECT 1 FROM users WHERE email = ?";
    // try (Connection con = JDBCUtil.getConnection();
    // PreparedStatement ps = con.prepareStatement(sql)) {
    //
    // ps.setString(1, email);
    // ResultSet rs = ps.executeQuery();
    // return rs.next();
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return false;
    // }

    public UserAccount login(String email, String password, String role) {

        String sql = """
                    SELECT * FROM users
                    WHERE email = ? AND password = ? AND role = ?
                """;

        try (Connection con = JDBCUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, role);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UserAccount u = new UserAccount();
                u.setId(rs.getInt("user_id"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean emailExists(String email) {

        String sql = "SELECT email FROM users WHERE email = ?";
        try (Connection con = JDBCUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUserAccount(UserAccount userAccount) {

        String sql = """
                    INSERT INTO users (email, password, role)
                    VALUES (?, ?, ?)
                """;

        try (Connection con = JDBCUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userAccount.getEmail());
            ps.setString(2, userAccount.getPassword());
            ps.setString(3, userAccount.getRole().toUpperCase());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePasswordByEmail(String email, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        try (Connection con = JDBCUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, email);

            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
