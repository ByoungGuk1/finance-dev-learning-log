package com.shinhan.day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shinhan.util.DBUtil;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 10. 오후 3:14:42 설명 : OracleExample2
 */
public class OracleExample2 {
	static Connection conn = null; // db연결
	static Statement st = null; // sql 전송 통로, 바인딩 변수 사용 불가
	static PreparedStatement pst = null; // sql 전송 통로, 바인딩 변수 사용 가능
	static ResultSet rs = null; // 결과

	public static void main(String[] args) {
		f_selectAll();
//		f_insert(1, "부서1", 100, 1700);
//		f_insert(2, "부서2", 100, 1700);
//		f_update(1, "개발부");
//		f_delete(2);
//		f_commit();
	}

	private static void f_commit() {
		String sql1 = "UPDATE departments set department_name = '영업부' WHERE department_id = 270";
		String sql2 = "INSERT INTO departments VALUES (280, '총무부', 100, 1700)";

		Statement st2 = null;

		conn = DBUtil.dbConnect();
		try {
			conn.setAutoCommit(false);
			st = conn.createStatement();
			int result1 = st.executeUpdate(sql1);
			st2 = conn.createStatement();
			int result2 = st2.executeUpdate(sql2);
			conn.commit();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
		}
	}

	private static void f_selectAll() {
		String sqlSelect = "SELECT * FROM departments ORDER BY 1";
		conn = DBUtil.dbConnect();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sqlSelect);
			while (rs.next()) {
				int departmentId = rs.getInt(1);
				String departmentName = rs.getString(2);
				int managerId = rs.getInt(3);
				int locationId = rs.getInt(4);
				System.out.printf("%d\t%s\t%d\t%d\n", departmentId, departmentName, managerId, locationId);
			}
		} catch (SQLException e) {
			System.err.println("OracleExample2.f_selectAll()-SQLException : " + e.getMessage());
		} finally {
			DBUtil.dbDisConnect(conn, st, rs);
		}
	}

	private static void f_insert(int departmentId, String departmentName, int managerId, int locationId) {
		String sqlInsert = "INSERT INTO departments VALUES (?, ?, ?, ?)";
		conn = DBUtil.dbConnect();
		try {
//			default : auto commit
//			conn.setAutoCommit(ture);
			pst = conn.prepareStatement(sqlInsert);
			pst.setInt(1, departmentId);
			pst.setString(2, departmentName);
			pst.setInt(3, managerId);
			pst.setInt(4, locationId);
			int result = pst.executeUpdate();
			System.out.println(result + "건 insert");
		} catch (SQLException e) {
			System.err.println("OracleExample2.f_insert()-SQLException : " + e.getMessage());
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}
	}

	private static void f_update(int departmentId, String departmentName) {
		String sqlUpdate = "UPDATE departments set department_name = ? WHERE department_id = ?";
		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sqlUpdate);
			pst.setInt(2, departmentId);
			pst.setString(1, departmentName);
			int result = pst.executeUpdate();
			System.out.println(result + "건 update");
		} catch (SQLException e) {
			System.err.println("OracleExample2.f_update()-SQLException : " + e.getMessage());
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}
	}

	private static void f_delete(int departmentId) {
		String sqlDelete = "DELETE FROM departments WHERE department_id = ?";
		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sqlDelete);
			pst.setInt(1, departmentId);
			int result = pst.executeUpdate();
			System.out.println(result + "건 delete");
		} catch (SQLException e) {
			System.err.println("OracleExample2.f_delete()-SQLException : " + e.getMessage());
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}
	}
}
