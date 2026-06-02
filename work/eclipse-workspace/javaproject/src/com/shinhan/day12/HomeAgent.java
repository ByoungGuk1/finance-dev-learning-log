package com.shinhan.day12;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 12:22:15 설명 : HomeAgent
 */
public class HomeAgent implements Rentable<Home> {
	@Override
	public Home rent() {
		return new Home();
	}
}
