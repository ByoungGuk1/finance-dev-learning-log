package com.shinhan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	public static Connection dbConnect() {
		Connection conn = null; // db연결
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "hr";
		String userPassword = "hr";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbUrl, userName, userPassword);
		} catch (ClassNotFoundException e) {
			System.err.println("DBUtil.dbConnect() - ClassNotFoundException : " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("DBUtil.dbConnect() - SQLException : " + e.getMessage());
		}

		return conn;
	}

	public static void dbDisConnect(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println("DBUtil.dbConnect() - SQLException : " + e.getMessage());
		}
	}
}
