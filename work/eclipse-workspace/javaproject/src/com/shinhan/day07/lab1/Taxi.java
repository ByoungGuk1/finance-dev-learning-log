package com.shinhan.day07.lab1;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 3:51:49
 * 설명			: Texi
 */
public class Taxi extends Vehicle{

	@Override
	public void run() {
		System.out.println(getClass().getSimpleName() + "가 달립니다.");
	}
	
}
