package com.shinhan.day09;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오전 10:24:30
 * 설명			: MyInterfaceImplement1
 */
//	interface를 구현한 class는 반드시 추상 메서드를 구현할 의무가 있다.
public class OracleJDBCImpl implements JDBCInterface {

	@Override
	public void getConnection() {
		System.out.println(getClass().getSimpleName()+"구현");
		System.out.println(MAX_VALUE);
		System.out.println(MAX_VALUE2);
		print();
		JDBCInterface.printStatic();
	}

	@Override
	public void getConnection2() {
		System.out.println("구현 class 에서 구현한 getConnection2()");
	}

	@Override
	public void print() {
		JDBCInterface.super.print();
		System.out.println("MyInterface의 default method를 구현 class에서 재정의");
	}
}
