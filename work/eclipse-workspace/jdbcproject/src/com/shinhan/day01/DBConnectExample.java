package com.shinhan.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 9. 오전 9:18:55 설명 : DBConnectExample
 */
public class DBConnectExample {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		1. JDBC Drive load
		Class.forName("oracle.jdbc.driver.OracleDriver"); // ClassNotFoundException
		System.out.println("JDBC Drive load");

//		2. Connection
		String dataBaseUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String pw = "hr";
		Connection conn = DriverManager.getConnection(dataBaseUrl, user, pw); // SQLException
		System.out.println("Connection");

//		3. SQL 작성
		int departmentId = 60;
		String sql = """
				SELECT *
				FROM EMPLOYEES
				WHERE DEPARTMENT_ID = ?
				""";

//		4. SQL 문장을 보낼 통로 만들기
//		Statement st = conn.createStatement();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, departmentId); // n번째 물음표에 값을 대입

//		5. SQL 문 전송 + 결과 수신
		ResultSet result = ps.executeQuery();

//		6. 결과 수신
		while (result.next()) {
			System.out.print(result.getInt(1) + " ");
			System.out.print(result.getString("first_name") + " ");
			System.out.println(result.getDouble("salary"));
		}

//		7. 자원 반납 (연결 통로 닫기 = DB 연결 해제)
		result.close();
		ps.close();
		conn.close();
	}
}
