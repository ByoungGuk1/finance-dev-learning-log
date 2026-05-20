package com.shinhan.day05;

public class Computer {
//	메서드 오버로딩 -> 이름이 같고 매개변수 사양(개수, 타입)이 다르다.
//	타입이 같은 것이 없으면 자동 형변환 하여 들어간다.
	String add(String num1, String num2) {
		return num1 + num2;
	}
	int add(int num1, int num2) {
		System.out.println("매개변수가 2개인 메서드 실행");
		return num1 + num2;
	}
	int add(int ... numbers) {
		System.out.println("매개변수가 가변길이인 메서드 실행");
		int total = 0;
		for(int number : numbers) {
			total += number;
		}
		return total;
	}
	double add(double num1, double num2) {
		return num1 + num2;
	}
}

