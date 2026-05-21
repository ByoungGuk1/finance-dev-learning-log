package com.shinhan.day06.LabReview.lab2;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: Employee
 */
public class Employee {
	private String id;
	private String name;
	private int baseSalary;
	
	double getSalary(double bonus) {
		return baseSalary + baseSalary * bonus;
	}
	
	public Employee() {;}
	public Employee(String id, String name, int baseSalary) {
		this.id = id;
		this.name = name;
		this.baseSalary = baseSalary;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(int baseSalary) {
		this.baseSalary = baseSalary;
	}
	
//	모든 클래스는 Object의 자식 객체로
//	이 Employee 클래스가 Object 클래스를 상속받고 있다.
//	따라서 toString을 오버라이딩 할 때
//	이름과 매개변수와 return을 동일하게 맞추어야하고
//	modifier-접근지정자의 범위가 같거나 더 넓게 설정해야한다.
	@Override
	public String toString() {
		return this.name + "(" + this.id + ") 사원의 기본급은 " + baseSalary + "원 입니다.";
	}
	
}
