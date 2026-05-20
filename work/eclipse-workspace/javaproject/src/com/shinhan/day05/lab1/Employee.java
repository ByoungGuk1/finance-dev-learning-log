package com.shinhan.day05.lab1;

public class Employee {
	private String name;
	private String title;
	private int baseSalary;
	private int totalSalary;
	
	public Employee(String name, String title, int baseSalary) {
		super();
		this.name = name;
		this.title = title;
		this.baseSalary = baseSalary;
		getTotalSalary();
	}

	public int getTotalSalary() {
		if(this.title.equals("부장")) {
			this.totalSalary = (int)(this.baseSalary * 1.25);
		} else if(this.title.equals("과장")) {
			this.totalSalary = (int)(this.baseSalary * 1.15);
		} else {
			this.totalSalary = (int)(this.baseSalary * 1.05);
		}
		return this.totalSalary;
	}
	
	public void print() {
		System.out.println(title + " 직급의 " + name + "씨의 본봉은 " + baseSalary + " 원이고 총급여는 " + totalSalary + " 원입니다.");
	}
}
