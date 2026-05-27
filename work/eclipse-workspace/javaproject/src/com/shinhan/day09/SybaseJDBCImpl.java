package com.shinhan.day09;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오전 10:30:25
 * 설명			: MyInterfaceImplement2
 */
public class SybaseJDBCImpl implements JDBCInterface {
	@Override
	public void getConnection() {
		System.out.println(getClass().getSimpleName()+"구현");
		System.out.println(MAX_VALUE+"---"+MAX_VALUE2);
		print();
		JDBCInterface.printStatic();
	}

	@Override
	public void getConnection2() {
		System.out.println("impl2 class 에서 구현한 getConnection2()");
	}
}
