package com.shinhan.day06;

import java.util.Optional;

import com.shinhan.day06.LabReview.lab2.Employee;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: Car
 */

//	Modifier - 접근권한 : public / protected / 생략 / private
/*
	*	public		- 모든 패키지에서 접근 가능
	*	protected	- 같은 패키지에서 접근 가능, 다른 패키지에선 상속 받은 경우 사용 가능
	*	생략(default)	- 같은 패키지에서 접근 가능, 다른 패키지에선 접근 불가
	*	private		- 같은 클래스 안에서만 접근 가능
*/
//	Modifier - 활용방법 : static, final, abstract, synchronized, transient
//		class 의 Modifier : public, 생략
//		field 의 Modifier : 접근권한 4개 + static, final, transient 
//		constructor 의 Modifier : 접근권한 4개 (싱글톤에서 private사용 ?)
//		method 의 Modifier : 접근권한 4개 + static, final, abstract, synchronized
public class Car {
	private String model;
	private String color;
	private int price;
//	static : 클래스 로드 시 자동 초기화, 객체 생성과 무관, class 변수
	private static int speed1;
//	final의 경우 마지막을 의미 -> 값을 할당하고 변경 불가
//		선언시, 생성시 값 할당 가능
	private final int speed2 = 100;
	private final int speed3;
//	final static / static final
//		클래스 로드시 자동 초기화, 선언 시 / static 블럭으로 초기화 (상수), 값 변경 불가
	static final int SPEED4 = 400;
	final static int SPEED5;
//	*transient* : 객체의 직렬화시에 제외한다.
//		직렬화 : 객체를 기타 방법으로 네트워크로 전송 시, byte형태로 변경해서 보낸다.
//		역직렬화 : 다시 받는 것
	transient int speed6;
	
	static{
		SPEED5 = 500;
	}
	
	public Car() {
		speed3 = 200;
//		생성자에선 static `초기화` 불가능
		System.out.println(SPEED5);
	}
	
	public static int getSpeed1() {
		return speed1;
	}
	public static void setSpeed1(int speed) {
		Car.speed1 = speed;
	}
	
//	함수의 오버로딩과 오버라이딩
//	override - 덮어쓰기
	@Override
	public boolean equals(Object obj) {
		return true;
	}
	@Override
	public String toString() {
		return "자동차";
	}
//	overload

//	method final -> 재정의 불가. 즉, 상속을 받아도 오버라이딩 불가
	public final void func1() {
		
	}
//	method abstract
//		-> 추상 메서드, 정의는 있고 구현은 없음. 구현은 자식이 
//		-> 추상 클래스에서만 사용 가능
//	protected abstract void func2();
	//	synchronized : multiThread에서 공유 자원을 여러 Thread가 접근하면
//		점유하고 있는 Thread가 Lock을 걸어서 공유 자원을 다른 Thread가 변경 못하도록 한다.
	synchronized void func3() {
		
	}
	private void func4() {
		
	}
	
//	void -> return의 값이 없다.
	void method1() {
		int score = 100;
		if(score < 0) {
			System.err.println("점수는 음수값 불가능");
			return;
		}
		System.out.println("점수는" + score);
	}
//	단일 값 return
	String method2() {
		int score = 100;
		if(score < 0) {
			System.err.println("점수는 음수값 불가능");
			return "FAIL";
		}
		System.out.println("점수는" + score);
		return "SUCCESS";
	}
// 여러 값 리턴 -> 클래스 활용
	Employee method3() {
		Employee emp = null;
		int score = 100;
		if(score < 0) {
			System.err.println("점수는 음수값 불가능");
			emp = new Employee("1", "김길동", -1);
		} else {
			System.out.println("점수는" + score);
			emp = new Employee("2", "박길동", 0);
		}
		return emp;
	}
}