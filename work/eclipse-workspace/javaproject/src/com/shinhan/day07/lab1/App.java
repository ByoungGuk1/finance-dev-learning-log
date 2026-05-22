package com.shinhan.day07.lab1;

/**
 * 작성자			: 송병국
 * 생성일 및 시간	: 2026. 5. 22. 오후 3:57:42
 * 설명			: App
 */
public class App {
	public static void main(String[] args) {
//		추상 클래스는 new를 사용하여 객체화 할 수 없다
//		Vehicle v = new Vehicle();
		
		Vehicle v1 = new Bus();
		Vehicle v2 = new Taxi();
		Driver driver = new Driver();
		
		driver.drive(v1);
		driver.drive(v2);
	}
}
