package com.shinhan.emp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shinhan.util.DBUtil;

public class EmpDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	Statement st = null;
	ResultSet rs = null;

//	부서, 직원 조인 조회

	public List<EmpJoinDTO> joinEmpDeptJobToClass(int deptId) {
		String sql = """
				select e.first_name, e.last_name, e.salary, d.department_name, j.job_title
				from employees e
				join departments d on e.department_id = d.department_id
				join jobs j on e.job_id = j.job_id
				where d.department_id = ?
				""";
		List<EmpJoinDTO> joinList = new ArrayList<>();
		conn = DBUtil.dbConnect();

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptId);
			rs = pst.executeQuery();

			while (rs.next()) {
				joinList.add(EmpJoinDTO.builder().firstName(rs.getString(1)).lastName(rs.getString(2))
						.salary(rs.getDouble(3)).departmentName(rs.getString(4)).jobTitle(rs.getString(5)).build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}

		return joinList;
	}

	public List<Map<String, Object>> joinEmpDeptJob(int deptId) {
		String sql = """
				select e.first_name, e.last_name, e.salary, d.department_name, j.job_title
				from employees e
				join departments d on e.department_id = d.department_id
				join jobs j on e.job_id = j.job_id
				where d.department_id = ?
				""";
		List<Map<String, Object>> datas = new ArrayList<>();
		conn = DBUtil.dbConnect();

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptId);
			rs = pst.executeQuery();

			while (rs.next()) {
				Map<String, Object> data = new HashMap<>();
				data.put("first_name", rs.getObject("first_name"));
				data.put("last_name", rs.getObject("last_name"));
				data.put("salary", rs.getObject("salary"));
				data.put("department_name", rs.getObject("department_name"));
				data.put("job_title", rs.getObject("job_title"));
				datas.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}

		return datas;
	}

//	sp호출
	public int spcall_raise_salary(int emplId, double commition) {
		int result = 0;
		CallableStatement cst = null;
		String sql = """
				{call raise_salary(?,?)}
				""";
		conn = DBUtil.dbConnect();
		try {
			cst = conn.prepareCall(sql);
			cst.setInt(1, emplId);
			cst.setDouble(2, commition);
			result += cst.execute() ? 1 : 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, cst, rs);
		}

		return result;
	}

//	여러 조건으로 직원들을 조회( 부서, 직책, 급여 >=, 입사일>= )
	public List<EmpDTO> selectByCondition(int deptId, String jobId, double salary, Date hireDate) {
		List<EmpDTO> empList = new ArrayList<>();
		String sql = """
				select * from employees
				where
					DEPARTMENT_ID = ?
					and job_id = upper(?)
					and salary >= ?
					and hire_date >= ?
				""";
		conn = DBUtil.dbConnect();

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptId);
			pst.setString(2, jobId);
			pst.setDouble(3, salary);
			pst.setDate(4, hireDate);

			rs = pst.executeQuery();
			while (rs.next()) {
				empList.add(buildVO(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}

		return empList;
	}

//	jobId로 직원 조회
	public List<EmpDTO> selectByJobId(String jobId) {
		List<EmpDTO> empList = new ArrayList<>();
		String sql = """
				select * from employees where job_id = upper(?)
				""";

		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, jobId);
			rs = pst.executeQuery();
			while (rs.next()) {
				empList.add(buildVO(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}
		return empList;
	}

//	특정 부서의 직원 조회
	public List<EmpDTO> selectByDepartmentId(int deptId) {
		List<EmpDTO> empList = new ArrayList<>();
		String sql = """
				select * from employees where department_id = ?
				""";

		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptId);
			rs = pst.executeQuery();
			while (rs.next()) {
				empList.add(buildVO(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisConnect(conn, pst, rs);
		}
		return empList;
	}

	public List<EmpDTO> selectAll() {
		List<EmpDTO> empList = new ArrayList<>();
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

	public EmpDTO selectById(int empid) {
		EmpDTO emp = null;
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

	public int insert(EmpDTO emp) {
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

	public int update(EmpDTO emp) {
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
				delete from employees where employee_id = ?
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

	private EmpDTO buildVO(ResultSet rs) throws SQLException {
		EmpDTO emp = EmpDTO.builder() //
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
