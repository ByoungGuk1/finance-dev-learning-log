package com.shinhan.day05;

public class CarExample {
	public static void main(String[] args) {
		Car car1 = new Car();
		Car car2 = new Car("BMW520");
		Car car3 = new Car("그랜저", 5000);
		
		System.out.println(car1);
		System.out.println(car2);
		System.out.println(car3);
		
//		자바의 최상위 클래스
//		Object obj = new Object();
//		System.out.println(obj);
		
//		class String extends Object
//		String s1 = new String("자바");
//		System.out.println(s1);
		
//		class Scanner extends Object
//		Scanner sc = new Scanner(System.in);
//		System.out.println(sc);
	}
}
