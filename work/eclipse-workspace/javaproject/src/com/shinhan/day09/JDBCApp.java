package com.shinhan.day09;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오전 10:35:24
 * 설명			: JDBCApp
 */
public class JDBCApp {

	public static void main(String[] args) {
		f1();
	}
	public static void f1() {
		OracleJDBCImpl jdbc = new OracleJDBCImpl();
		SybaseJDBCImpl jdbc2 = new SybaseJDBCImpl();
		work(jdbc);
		work(jdbc2);
	}

	private static void work(JDBCInterface inter) {
		System.out.println(JDBCInterface.MAX_VALUE);
		System.out.println(JDBCInterface.MAX_VALUE2);
		
		inter.getConnection();	//	추상구현
		inter.getConnection2();	//	추상구현
		inter.print();	//	default method
		
		JDBCInterface.printStatic();
	}
}
