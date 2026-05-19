package com.shinhan.day04.chap6;

public class CustomerExample {
	public static void main(String[] args) {
		f1();
	}
	private static void f1() {
//		객체 참조 변수 선언
		CustomerVO cust1;
//		객체 생성
		cust1 = new CustomerVO();
//		객체 사용
		System.out.println("1번 객체");
		System.out.println(cust1.customerName);
		cust1.infoPrint();
		System.out.println();
		
//		객체 선언 + 생성
		CustomerVO cust2 = new CustomerVO();
		System.out.println("2번 객체");
		System.out.println(cust1.customerName);
		cust2.infoPrint();
		System.out.println();
		
		System.out.println("3번 객체");
		CustomerVO cust3 = new CustomerVO("홍길동", "010-1234-5678", 1);
		cust3.infoPrint();
	}
}
