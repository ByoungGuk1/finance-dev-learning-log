package com.shinhan.day04;

import java.util.List;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 12. 오전 9:39:04 설명 : EmpService
 */
public class EmpService {
	EmpDAO empDAO = new EmpDAO();

	public List<EmpVO> selectAllService() {
		List<EmpVO> empList = empDAO.selectAll();
		return empList;
	}

	public EmpVO selectByIdService(int empid) {
		EmpVO emp = empDAO.selectById(empid);
		return emp;
	}

	public int insertService(EmpVO emp) {
		int result = empDAO.insert(emp);
		return result;
	}

	public int updateService(EmpVO emp) {
		int result = empDAO.update(emp);
		return result;
	}

	public int deleteService(int empId) {
		int result = empDAO.delete(empId);
		return result;
	}
}
