package com.shinhan.day04.chap6;

//	class = 설계도
//	설계도 작성
public class CustomerVO {
//	1. field 데이터 저장 목적 (=변수)
	String customerName;
	String phoneNumber;
	int grade;
	
//	2. 생성자
	CustomerVO(){
		super();
	}
	public CustomerVO(String customerName) {
		super();
		this.customerName = customerName;
	}
	public CustomerVO(String customerName, String phoneNumber) {
		super();
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
	}
	CustomerVO(String name, String phoneNumber, int grade){	//	생성자 오버로딩
		super();
		this.customerName = name;
		this.phoneNumber = phoneNumber;
		this.grade = grade;
	}
	
//	3. 메서드
	void infoPrint() {
		System.out.println("이름: " + customerName);
		System.out.println("전화번호: " + phoneNumber);
		System.out.println("등급: " + grade);
	}
}
