package com.shinhan.day01;

/*
 * 하나의 .java에 여러 class를 정의 가능하다
 * 하나의 클래스만 public으로 가능하다.
 * */

/*
 * Java 클래스 구성요소
 * 1. field (변수)
 * 2. Constructor (생성자)
 * 3. method (함수)
 * 4. instance block, static block { }
 * 5. inner class
 * */

class MoreClass{
	
}

public class Hello {

	public static void func1() {
		System.out.println("함수1");
	}
	public static void func2() {
		System.out.println("함수2");
	}
	
	public static void main(String[] args) {
		System.out.println("안녕하세요");
		func1();
		func2();
	}

}
