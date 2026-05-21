package com.shinhan.day06.LabReview.lab1;

/**
 * 작성자	: 송병국
 * 작성일	: 2026. 5. 21.
 * 설명	: EmployeeExam
 */

public class EmployeeExam {
	public static void main(String[] args) {
		Employee[] empList = new Employee[4];
		empList[0] = new Employee("이부장", "부장", 1500000);
		empList[1] = new Employee("김과장", "과장", 1300000);
		empList[2] = new Employee("최대리", "대리", 1200000);
		empList[3] = new Employee("박사원", "사원", 1000000);
		
		for(Employee data : empList) {
			data.print();
		}
	}
}
