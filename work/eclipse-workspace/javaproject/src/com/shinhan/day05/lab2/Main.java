package com.shinhan.day05.lab2;

public class Main {
	public static void main(String[] args) {
		Employee em = new Employee("123", "홍길동", 100000);
		
		System.out.println("getSalary(1.1) : " + em.getSalary(1.1));
		System.out.println(em);
	}
}
