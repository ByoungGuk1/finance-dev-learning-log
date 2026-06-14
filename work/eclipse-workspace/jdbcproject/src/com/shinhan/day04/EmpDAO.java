package com.shinhan.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.util.DBUtil;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 12. 오전 9:47:42 설명 : EmpDAO
 */
public class EmpDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	Statement st = null;
	ResultSet rs = null;

	public List<EmpVO> selectAll() {
		List<EmpVO> empList = new ArrayList<>();
		String sql = """
				select * from employees
				""";

		conn = DBUtil.dbConnect();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				empList.add(buildVO(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, st, rs);
		}
		return empList;
	}

	public EmpVO selectById(int empid) {
		EmpVO emp = null;
		String sql = """
				select * from employees where employee_id = ?
				""";

		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, empid);
			rs = pst.executeQuery();
			if (rs.next() == true) {
				emp = buildVO(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}
		return emp;
	}

	public int insert(EmpVO emp) {
		int result = 0;
		String sql = """
				insert into employees values(?,?,?,?,?,?,?,?,?,?,?)
				""";

		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, emp.getEmployeeId());
			pst.setString(2, emp.getFirstName());
			pst.setString(3, emp.getLastName());
			pst.setString(4, emp.getEmail());
			pst.setString(5, emp.getPhoneNumber());
			pst.setDate(6, emp.getHireDate());
			pst.setString(7, emp.getJobId());
			pst.setDouble(8, emp.getSalary());
			pst.setDouble(9, emp.getCommissionPct());
			pst.setInt(10, emp.getManagerId());
			pst.setInt(11, emp.getDepartmentId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}
		return result;
	}

	public int update(EmpVO emp) {
		int result = 0;
		String sql = """
				update employees
				set
				FIRST_NAME = ?,
				LAST_NAME = ?,
				EMAIL = ?,
				PHONE_NUMBER = ?,
				HIRE_DATE = ?,
				JOB_ID = ?,
				SALARY = ?,
				COMMISSION_PCT = ?,
				MANAGER_ID = ?,
				DEPARTMENT_ID = ?
				where EMPLOYEE_ID = ?
				""";

		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, emp.getFirstName());
			pst.setString(2, emp.getLastName());
			pst.setString(3, emp.getEmail());
			pst.setString(4, emp.getPhoneNumber());
			pst.setDate(5, emp.getHireDate());
			pst.setString(6, emp.getJobId());
			pst.setDouble(7, emp.getSalary());
			pst.setDouble(8, emp.getCommissionPct());
			pst.setInt(9, emp.getManagerId());
			pst.setInt(10, emp.getDepartmentId());
			pst.setInt(11, emp.getEmployeeId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}
		return result;
	}

	public int delete(int empId) {
		int result = 0;
		String sql = """
				delete from employees where employee_id = ? cascading
				""";

		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, empId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}
		return result;
	}

	private EmpVO buildVO(ResultSet rs) throws SQLException {
		EmpVO emp = EmpVO.builder() //
				.employeeId(rs.getInt(1)) //
				.firstName(rs.getString(2)) //
				.lastName(rs.getString(3)) //
				.email(rs.getString(4)) //
				.phoneNumber(rs.getString(5)) //
				.hireDate(rs.getDate(6)) //
				.jobId(rs.getString(7)) //
				.salary(rs.getDouble(8)) //
				.commissionPct(rs.getDouble(9)) //
				.managerId(rs.getInt(10)) //
				.departmentId(rs.getInt(11)) //
				.build();
		return emp;
	}
}
