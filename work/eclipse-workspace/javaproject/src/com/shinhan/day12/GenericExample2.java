package com.shinhan.day12;

import lombok.ToString;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 1. 오후 12:08:00 설명 : GenericExample2
 */
@ToString
class TV {

}

@ToString
class Car {
	public void run() {
		System.out.println("자동차가 달립니다.");
	}
}

class Home {
	public void turnOnLight() {
		System.out.println("전등을 켭니다.");
	}
}

public class GenericExample2 {
	public static void main(String[] args) {
//		f1();
		f2();
	}

	private static void f1() {
		Product<TV, String> p1 = new Product<>();
		p1.setKind(new TV());
		p1.setModel("ABC_MODEL");
		System.out.println(p1);

		Product<Car, String> p2 = new Product<>();
		p2.setKind(new Car());
		p2.setModel("ABC_MODEL");
		System.out.println(p2);
	}

	private static void f2() {
		HomeAgent homeAgent = new HomeAgent();
		Home home = homeAgent.rent();
		home.turnOnLight();

		CarAgent carAgent = new CarAgent();
		Car car = carAgent.rent();
		car.run();
	}
}
