package com.shinhan.day09;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 27. 오전 10:12:10
 * 설명			: MyInterface
 */
//	interface : 규격서(규칙), 상수와 추상 메서드로 구성되어있다. 생성자 없다 -> instance 생성불가
//		버전이 올라가면서 추가
//			1. default method
//			2. static method
//			3. private method
//			4. private static method
public interface JDBCInterface {
	int MAX_VALUE = 1000;	// -> public final static 생략되어있다.
	public final static int MAX_VALUE2 = 2000; 
	
	void getConnection();	//	-> public abstract 생략되어있다.
	public abstract void getConnection2();
	
	public default void print() {
		System.out.println("MyInterface를 구현한 class에 공통, 재정의 가능");
		printPrivate();
	}
	public static void printStatic() {	//	interface의 메서드
		System.out.println("MyInterface를 구현한 class에 공통, 재정의 불가");
		printPrivateStatic();
	}
		
	private void printPrivate() {
		System.out.println("MyInterface 내부에서만 사용 가능, non-static");
	}
	private static void printPrivateStatic() {
		System.out.println("MyInterface 내부에서만 사용 가능, static");
	}
}
