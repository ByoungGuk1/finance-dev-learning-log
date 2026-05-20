package com.shinhan.day05;

public class Calculator {
//	필드		- data 저장, 소문자로 시작
//	private int channel;
	private static int score1 = 100;
//	private int score2 = 80;
//	생성자
	public Calculator() {;}
//	메서드	- 기능, 소문자로 시작, return 타입의 정의가 필수
//		[modifier] [returnType] 메서드명 (매개변수) { ... }
//	modifier
//		접근 권한
//			public		: 같은 패기지 O, 다른 패키지 O
//			protected	: 같은 패기지 O, 다른 패키지 △ (상속 받으면 접근 가능)
//			생략			: 같은 패기지 O, 다른 패키지 X
//			private		: 같은 패키지 X, 다른 패키지 X -> 해당 클래스에서만 접근 가능
//		활용 방법
//			static		: static -> class method, 객체 생성 없이 사용 가능, static변수등 static이여야만 사용 가능
//			final		: 최종 -> 더 이상 상속받지 않음
//			abstract	: 
//			synchronized: 멀티스레드에서 사용, 공유데이터에 lock을 걸어서 접근을 막음
	public static void powerOff1() {;}
	static void powerOff2() {
		System.out.println("static method");
		System.out.println(score1);	//	static 변수 사용 가능
//		System.out.println(score2);	//	non-static 변수 사용 불가
	}
	public void powerOn1() {	powerOn4();	}
	protected void powerOn2() {	powerOn4();	}
	void powerOn3() {	powerOn4();	}
	private void powerOn4() {
		System.out.println("private는 class 내부에서만 접근이 가능하다");
	}
	
}
