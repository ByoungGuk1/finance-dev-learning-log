package com.shinhan.day04;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * 작성자 : 송병국 생성일 및 시간 : 2026. 6. 12. 오전 9:39:04 설명 : EmpService
 */
public class EmpService {
	EmpDAO empDAO = new EmpDAO();

	public List<EmpJoinDTO> joinEmpDeptJobToClass(int deptId) {
		return empDAO.joinEmpDeptJobToClass(deptId);
	}

	public List<Map<String, Object>> joinEmpDeptJob(int deptId) {
		return empDAO.joinEmpDeptJob(deptId);
	}

	public int spcall_raise_salary(int emplId, double commition) {
		return empDAO.spcall_raise_salary(emplId, commition);
	}

	public List<EmpVO> selectByConditionService(int deptId, String jobId, double salary, Date hireDate) {
		List<EmpVO> empList = empDAO.selectByCondition(deptId, jobId, salary, hireDate);
		return empList;
	}

	public List<EmpVO> selectByJobIdService(String jobId) {
		List<EmpVO> empList = empDAO.selectByJobId(jobId);
		return empList;
	}

	public List<EmpVO> selectByDeptIdService(int deptId) {
		List<EmpVO> empList = empDAO.selectByDepartmentId(deptId);
		return empList;
	}

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
