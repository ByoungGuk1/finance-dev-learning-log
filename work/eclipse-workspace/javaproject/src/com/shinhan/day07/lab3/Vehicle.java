package com.shinhan.day07.lab3;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 5:00:01
 * 설명			: Vehicle
 */
public class Vehicle {
	private String name;
	private int speed;
	
	public Vehicle() {;}
	public Vehicle(String name, int speed) {
		super();
		this.name = name;
		this.speed = speed;
	}
	
	public void start() {
		System.out.println(name + " 출발합니다. (속도: " + speed + "km/h)");
	}
	public void stop() {
		System.out.println(name + " 멈춥니다.");
	}
	public void info() {
		System.out.println("차량명: " + name + ", 최고속도: " + speed + "km/h");
	}
}
