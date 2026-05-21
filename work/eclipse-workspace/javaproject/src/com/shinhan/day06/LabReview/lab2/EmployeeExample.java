package com.shinhan.day06.LabReview.lab2;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: EmployeeExample
 */
public class EmployeeExample {
	public static void main(String[] args) {
		Employee[] empList = new Employee[3];
		empList[0] = new Employee("123", "이부장", 1500000);
		empList[1] = new Employee("456", "김과장", 1300000);
		empList[2] = new Employee("789", "최대리", 1200000);
		
		for(Employee data : empList) {
			System.out.println(data + "\n급여는 " + data.getSalary(0.1) + "입니다.");
		}
	}
}
