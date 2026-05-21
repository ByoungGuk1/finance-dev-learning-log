package com.shinhan.day06;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: CarExample
 */
public class CarExample {
	public static void main(String[] args) {
		Car c1 = new Car();
		Car c2 = new Car();
		
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c1.equals(c2));
	}
	public static void main1(String[] args) {
		System.out.println(Car.getSpeed1());
	}
}
