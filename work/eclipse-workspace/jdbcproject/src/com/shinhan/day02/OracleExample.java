package com.shinhan.day02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 10. 오전 9:21:54 설명 : OracleExample
 */
public class OracleExample {
	public static void main(String[] args) {
		Connection conn = null; // db연결
		Statement st = null; // sql 전송 통로, 바인딩 변수 사용 불가
//		PreparedStatement pst = null; // sql 전송 통로, 바인딩 변수 사용 가능
		ResultSet rs = null; // 결과

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbId = "hr";
		String dbPw = "hr";

		String sql = """
				SELECT job_id, MAX(employee_id) AS "MAX_EM_ID", MIN(first_name) AS "MIN_EM_FIRST_NAME", SUM(salary) AS "SUM_EM_SALARY", MIN(hire_date) AS "MIN_EM_HIRE_DATE"
				FROM employees
				WHERE department_id = 50
				GROUP BY job_id
				HAVING SUM(salary) >= 50000
				ORDER BY job_id
				""";

		try {
//			1. JDBC driver load (먼저 클래스 path에 JDBC 라이브러리가 존재)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("JDBC driver load");

//			2. connection
			conn = DriverManager.getConnection(url, dbId, dbPw);
			System.out.println("connection");

//			3. db에 요청 후 응답 결과 저장 (Java APP Memory에 있음)
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			System.out.println("db에 요청 후 응답 결과 저장");

//			4. 응답 받은 결과를 java에 맞게 수정
			while (rs.next()) {
				String jobId = rs.getString("job_id");
				int employeeId = rs.getInt("MAX_EM_ID");
				String firstName = rs.getString("MIN_EM_FIRST_NAME");
				double salary = rs.getDouble("SUM_EM_SALARY");
				Date hireDate = rs.getDate("MIN_EM_HIRE_DATE");
				System.out.printf("%s %d %s %f %s\n", jobId, employeeId, firstName, salary, hireDate);
			}
		} catch (ClassNotFoundException e) {
			System.err.println("OracleExample.main() - ClassNotFoundException : " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("OracleExample.main() - SQLException : " + e.getMessage());
		}

	}
}
