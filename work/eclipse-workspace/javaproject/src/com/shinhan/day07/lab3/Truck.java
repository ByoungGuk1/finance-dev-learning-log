package com.shinhan.day07.lab3;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 5:12:06
 * 설명			: Truck
 */
public class Truck extends Vehicle{
	private int maxLoad;
	
	public Truck(String name, int speed, int maxLoad) {
		super(name, speed);
		this.maxLoad = maxLoad;
	}

	@Override
	public void info() {
		super.info();
		System.out.println("최대 적재량: " + maxLoad + "kg");
	}
}
