package com.shinhan.day05;

public class ObjectExample {

	public static void main(String[] args) {
		f1();
		f2();
	}

	private static void f1() {
		Korean p1 = new Korean("홍길동", "12345");
		System.out.println(p1);
		p1.setName("개명");
		System.out.println(p1.getName());
	}
	private static void f2() {
		Calculator c1 = new Calculator();
		c1.powerOn1();
		c1.powerOn2();
		c1.powerOn3();
//		c1.powerOn4();	//	접근 불가
		
		Calculator.powerOff1();
		Calculator.powerOff2();
		
		String s1 = "자바";
//		instance method
		s1.toLowerCase();
//		static method
		String.join(s1, "***");
		
	}
}
