package com.shinhan.day06.LabReview.lab1;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: Employee
 */

//	자바 빈즈 기술 : field를 private, 기본 생성자, getter/setter
public class Employee {
//	1. field (멤버변수)	/ static(class 변수), non-static(instatnce 변수)
	private String name;
	private String title;
	private int baseSalary;
	private int totalSalary;
	
//	생성자를 정의하지 않으면 컴파일 시 기본 생성자를 제공
//	생성자를 정의하면 기본 생성자는 제공되지 않는다.
//	생성자 -> 초기화를 목적으로 사용
//	이름 충돌 발생시에 this 사용, 생성자가 모양이 다른 생성자(오버로딩) 호출시
//	this : 해당 객체, 현재 객체
	public Employee() {
		this(null, null, 0);
	}
	public Employee(String name, String title, int baseSalary) {
		this.name = name;
		this.title = title;
		this.baseSalary = baseSalary;
		getTotalSalary();
	}
	
	public void getTotalSalary() {
		if(title.equals("부장")) {
			totalSalary = (int)(baseSalary + baseSalary * 0.25);
		}else if(title.equals("과장")) {
			totalSalary = (int)(baseSalary + baseSalary * 0.15);
		}else {
			totalSalary = (int)(baseSalary + baseSalary * 0.05);
		}
	}
	
	public void print() {
		System.out.printf("%s 직급의 %s씨의 본봉은 %d원이고 총 급여는 %d원입니다.\n", title, name, baseSalary, totalSalary);
	}
}
