package org.pensions.data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnManagerPMS {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.100.145:3306/pms_demo";
    static final String USER = "root";
    static final String PASS = "root";

    public Connection getConnection() {
        Connection dbConn = null;
        try {
            Class.forName(JDBC_DRIVER);
            dbConn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dbConn;
    }

    public void closeConnection(Connection dbConn) {
        try {
            dbConn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
