package com.shinhan.day12;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 12:23:02 설명 : CarAgent
 */
public class CarAgent implements Rentable<Car> {
	@Override
	public Car rent() {
		return new Car();
	}
}
