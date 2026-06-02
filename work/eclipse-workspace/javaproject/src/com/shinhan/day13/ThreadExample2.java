package com.shinhan.day13;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 2. 오전 10:49:12 설명 : ThreadExample2
 */
public class ThreadExample2 {
	public static void main(String[] args) {
		Calculator cal = new Calculator();
		User1Thread u1 = new User1Thread();
		User2Thread u2 = new User2Thread();

		u1.setCalculator(cal);
		u2.setCalculator(cal);

		u1.start();
		u2.start();
	}
}
