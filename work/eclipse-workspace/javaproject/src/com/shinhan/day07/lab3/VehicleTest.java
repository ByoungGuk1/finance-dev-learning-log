package com.shinhan.day07.lab3;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 5:14:15
 * 설명			: VehicleTest
 */
//개선 사항 찾기
public class VehicleTest {
	public static void main(String[] args) {
		Vehicle[] arr = {
				new Car("승용차", 180, 15),
				new Bus("시내버스", 100, 40),
				new Truck("화물트럭", 90, 5000)
				};
		for(Vehicle v : arr) {
			v.info();
			v.start();
			v.stop();
			System.out.println("---------------");
		}
	}
}
