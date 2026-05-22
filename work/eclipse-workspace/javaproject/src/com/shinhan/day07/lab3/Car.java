package com.shinhan.day07.lab3;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 5:01:35
 * 설명			: Car
 */
public class Car extends Vehicle{
	private int gasMileage;

	public Car(String name, int speed, int gasMileage) {
		super(name, speed);
		this.gasMileage = gasMileage;
	}
	
	@Override
	public void info() {
		super.info();
		System.out.println("연비: " + gasMileage + "km/L");
	}
}
