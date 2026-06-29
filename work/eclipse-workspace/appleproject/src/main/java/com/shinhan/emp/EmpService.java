package com.shinhan.emp;

import java.sql.Date;
import java.util.List;
import java.util.Map;

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

	public List<EmpDTO> selectByConditionService(int deptId, String jobId, double salary, Date hireDate) {
		List<EmpDTO> empList = empDAO.selectByCondition(deptId, jobId, salary, hireDate);
		return empList;
	}

	public List<EmpDTO> selectByJobIdService(String jobId) {
		List<EmpDTO> empList = empDAO.selectByJobId(jobId);
		return empList;
	}

	public List<EmpDTO> selectByDeptIdService(int deptId) {
		List<EmpDTO> empList = empDAO.selectByDepartmentId(deptId);
		return empList;
	}

	public List<EmpDTO> selectAllService() {
		List<EmpDTO> empList = empDAO.selectAll();
		return empList;
	}

	public EmpDTO selectByIdService(int empid) {
		EmpDTO emp = empDAO.selectById(empid);
		return emp;
	}

	public int insertService(EmpDTO emp) {
		int result = empDAO.insert(emp);
		return result;
	}

	public int updateService(EmpDTO emp) {
		int result = empDAO.update(emp);
		return result;
	}

	public int deleteService(int empId) {
		int result = empDAO.delete(empId);
		return result;
	}
}
