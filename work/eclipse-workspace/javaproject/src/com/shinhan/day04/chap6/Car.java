package com.shinhan.day04.chap6;
/*
 * class : object 만드는 틀, 설계도 template, object 타입으로 사용
 * object : (=instance) 실체, class를 이용해서 만든 독립된 개체
 * */
//	[modifier] class class명 extends/implements 부모클래스/인터페이스 명 { ... } 
//	[접근지정자] class [클래스명] [종속명령어] [받을개체] { ... }
public class Car {
	/*
	 * 1. 필드-field
	 * 	data 저장 목적, 변수, 속성
	 * 2. 생성자-constructor
	 * 	객체를 생성하는 방법
	 * 	컴파일 시, 자동으로 기본 생성자가 생성된다.
	 * 3. 메서드-method
	 * 	기능, 함수
	 * 4. block (?)
	 * 	{ ... }
	 * 	문장들
	 * 5. inner class (?)
	 * 	클래스 내부에 있는 클래스
	 * */
	String model;
	int price;
	
	Car() {	//	기본 생성자
		super();
		System.out.println("Car 클래스 생성");
	}
	Car(String model, int price) {	//	생성자 오버로딩
		super();
		this.model = model;	//	매개변수와 필드의 변수(멤버변수) 명이 충돌 -> 구별이 필요 -> field는 이 class로 만든 현재 객체라는 것을 명시 : this
		this.price = price;
		System.out.println("Car 클래스 생성(생성자 오버로딩 - argument 2개)");
	}
	
//	함수
//	[modifier] [returnType] 함수이름 (매개변수) { ... }
	void f_info() {
		System.out.println("model = " + this.model);
		System.out.println("price = " + this.price);
	}
}
