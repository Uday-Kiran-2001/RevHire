package com.rev_hire.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.io.InputStream;

public class JDBCUtil {

    private static String url;
    private static String username;
    private static String password;

    static {
        try (InputStream is = JDBCUtil.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            Properties props = new Properties();
            props.load(is);

            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JDBCUtil() {}

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(url, username, password);

            System.out.println("Connected as user: " +
                    con.getMetaData().getUserName());

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT SYS_CONTEXT('USERENV','CON_NAME') FROM dual"
            );
            if (rs.next()) {
                System.out.println("DB Container : " + rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }


}