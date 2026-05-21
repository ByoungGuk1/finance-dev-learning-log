package com.shinhan.day06;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: Calculator
 */
public class Calculator {
	final int score;
	static final int SCORE2;
	static double pi = 3.141592;
	static double pi2;
	static double pi3;
	double pi4 = 3.1415;
	static {
		SCORE2 = 200;
		pi2 = 3.14;
//		pi4 = 3;	//	non-static으로 사용 불가
	}
	{
//		final은 변경 불가
		score = 111;
//		SCORE2 = 222;
	}
	public Calculator() {
//		score = 100;
//		static 변수의 값을 변경
		pi3 = 3.141;
		pi4 = 3;
	}
	
//	instance 메서드에서 static 변수 사용 가능
	public void f1() {
		System.out.println(pi);
		System.out.println(pi2);
		System.out.println(pi3);
		System.out.println(pi4);
	}
//	static 메서드에서 instance 변수 사용 불가
	public static void f2() {
		System.out.println(pi);
		System.out.println(pi2);
		System.out.println(pi3);
//		System.out.println(pi4);	//	non-static으로 사용 불가
	}
}
