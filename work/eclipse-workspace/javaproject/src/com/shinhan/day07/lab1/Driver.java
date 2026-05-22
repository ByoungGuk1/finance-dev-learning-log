package com.shinhan.day07.lab1;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 3:54:23
 * 설명			: Driver
 */
//	매개변수의 타입은 동일하지만, 전달되는 객체가 달라져서 실행 결과가 다양하게 나올 수 있다.
//		=> 다형성
public class Driver {
	public void drive(Vehicle vechicle) {
		vechicle.run();
	}
}
