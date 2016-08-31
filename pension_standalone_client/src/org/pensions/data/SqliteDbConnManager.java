package org.pensions.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteDbConnManager {

	public Connection getConnection() throws Exception {
		String connectionURL = "jdbc:sqlite:test.db";
		Connection dbConn = null;
		try {
			
			Class.forName("org.sqlite.JDBC");
			dbConn = DriverManager.getConnection(connectionURL);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return dbConn;
	}
	
	public void closeConnection(Connection dbConn) {
		
		try {
			dbConn.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
}
