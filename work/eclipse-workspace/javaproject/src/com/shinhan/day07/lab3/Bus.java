package com.shinhan.day07.lab3;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 5:08:16
 * 설명			: Bus
 */
public class Bus extends Vehicle {
	private int passengerCount;
	
	public Bus(String name, int speed, int passengerCount) {
		super(name, speed);
		this.passengerCount = passengerCount;
	}

	@Override
	public void info() {
		super.info();
		System.out.println("승객 수용 가능 인원: " + passengerCount + "명");
	}
}
