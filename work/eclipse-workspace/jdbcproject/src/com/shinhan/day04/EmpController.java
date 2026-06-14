package com.shinhan.day04;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 12. 오전 9:31:13 설명 : EmpController
 */
public class EmpController {
	private static EmpService empService = new EmpService();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		boolean isStop = false;
		while (!isStop) {
			EmpView.menuDisplay();
			String selectedNum = sc.nextLine();
			switch (selectedNum) {
			case "1" -> f_selectAll();
			case "2" -> f_selectById();
			case "3" -> f_insert();
			case "4" -> f_update();
			case "5" -> f_delete();
			case "0" -> isStop = true;
			default -> {
				System.out.println("다시 입력해주세요");
			}
			}
		}
		System.out.println("프로그램 종료");
	}

	private static void f_selectAll() {
		List<EmpVO> empList = empService.selectAllService();
		EmpView.print(empList);
	}

	private static void f_selectById() {
		int empid = 0;
		System.out.print("조회할 id 입력 (100 ~ 206) > ");
		empid = sc.nextInt();
		sc.nextLine();
		EmpVO emp = empService.selectByIdService(empid);
		EmpView.print(emp);
	}

	private static void f_insert() {
		EmpVO emp = inputEmp();
		int result = empService.insertService(emp);
		EmpView.print("insert", result);
	}

	private static void f_update() {
		EmpVO emp = inputEmp();
		int result = empService.updateService(emp);
		EmpView.print("update", result);
	}

	private static void f_delete() {
		int empId = 0;
		System.out.println("삭제할 직원 번호 입력 > ");
		empId = sc.nextInt();
		sc.nextLine();
		int result = empService.deleteService(empId);
		EmpView.print("delete", result);
	}

	private static EmpVO inputEmp() {
		System.out.println("직원 번호 입력 (필수) > ");
		int empId = Integer.parseInt(sc.nextLine().trim());
		System.out.println("직원 fisrtName 입력 > ");
		String firstName = sc.nextLine().trim();
		System.out.println("직원 lastName 입력 (필수) > ");
		String lastName = sc.nextLine().trim();
		System.out.println("직원 email 입력 (필수) > ");
		String email = sc.nextLine().trim();
		System.out.println("직원 phoneNumber 입력 > ");
		String phoneNumber = sc.nextLine().trim();

//		Date.valueOf("2026-01-02")

		System.out.println("직원 salary 입력 > ");
		double salary = sc.nextDouble();
		sc.nextLine();
		System.out.println("직원 commissionPct 입력 > ");
		double commissionPct = sc.nextDouble();
		sc.nextLine();
		System.out.println("직원 managerId 입력 > ");
		int managerId = Integer.parseInt(sc.nextLine().trim());
		System.out.println("직원 departmentId 입력 > ");
		int departmentId = Integer.parseInt(sc.nextLine().trim());

		EmpVO emp = EmpVO.builder() //
				.employeeId(empId) //
				.firstName(firstName.isEmpty() ? null : firstName) //
				.lastName(lastName) //
				.email(email.isEmpty() ? null : email) //
				.phoneNumber(phoneNumber.isEmpty() ? null : phoneNumber) //
				.hireDate(new Date(new java.util.Date().getTime())) //
				.jobId("IT_PROG") //
				.salary(salary) //
				.commissionPct(commissionPct) //
				.managerId(managerId) //
				.departmentId(departmentId) //
				.build();
		return emp;
	}
}
