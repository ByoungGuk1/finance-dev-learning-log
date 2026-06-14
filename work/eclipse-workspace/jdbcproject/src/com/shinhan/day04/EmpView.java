package com.shinhan.day04;

import java.util.List;

// view : 나중에 웹으로 전환
public class EmpView {
	public static void menuDisplay() {
		System.out.println("--------------");
		System.out.println("1. 조회 | 2. 상세조회 | 3. 입력 | 4. 수정 | 5. 삭제 | 0. 종료");
		System.out.println("--------------");
		System.out.print("작업 선택 > ");
	}

	public static void print(String message, int resultCount) {
		System.out.println("[알림] " + resultCount + "건 " + message);
	}

	public static void print(EmpVO emp) {
		System.out.println("=======한건 출력========");
		if (emp == null) {
			System.out.println("정보가 없습니다.");
			return;
		}
		System.out.println("EmployeeId : " + emp.getEmployeeId());
		System.out.println("FirstName : " + emp.getFirstName());
		System.out.println("LastName : " + emp.getLastName());
		System.out.println("Email : " + emp.getEmail());
		System.out.println("PhoneNumber : " + emp.getPhoneNumber());
		System.out.println("HireDate : " + emp.getHireDate());
		System.out.println("JobId" + emp.getJobId());
		System.out.println("Salary : " + emp.getSalary());
		System.out.println("CommissionPct : " + emp.getCommissionPct());
		System.out.println("ManagerId : " + emp.getManagerId());
		System.out.println("DepartmentId : " + emp.getDepartmentId());
	}

	public static void print(List<EmpVO> blist) {
		System.out.println("====== 직원 목록 ======");
		blist.stream().forEach(System.out::println);
	}
}
