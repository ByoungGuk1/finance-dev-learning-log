package com.shinhan.day04;

import java.util.List;
import java.util.Map;

// view : 나중에 웹으로 전환
public class EmpView {
	public static void menuDisplay() {
		System.out.println("--------------");
		System.out.println("1. 조회 | 2. 상세조회 | 3. 입력 | 4. 수정 | 5. 삭제 |"//
				+ " 6. 특정 부서로 직원 조회 | 7. 특정 직무로 직원 조회 | 8. 특정 조건으로 직원 조회 |"//
				+ " 9. 프로시저 호출 | 0. 종료");
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
		System.out.println("JobId : " + emp.getJobId());
		System.out.println("Salary : " + emp.getSalary());
		System.out.println("CommissionPct : " + emp.getCommissionPct());
		System.out.println("ManagerId : " + emp.getManagerId());
		System.out.println("DepartmentId : " + emp.getDepartmentId());
	}

	public static void print(List<EmpVO> blist) {
		System.out.println("====== 직원 목록 ======");
		if (blist.size() == 1) {
			System.out.println("단일 결과로 세부 목록으로 표시됩니다.");
			print(blist.get(0));
			return;
		}
		blist.stream().forEach(System.out::println);
	}

	public static void printMap(List<Map<String, Object>> datas) {
		datas.stream().forEach((data) -> {
			for (String key : data.keySet()) {
				System.out.println(key + " : " + data.get(key));
			}
			System.out.println();
		});
	}

	public static void printJoinDTOs(List<EmpJoinDTO> datas) {
		datas.stream().forEach((data) -> {
			System.out.println(data);
		});
	}
}
