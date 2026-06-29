package com.shinhan.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinhan.emp.EmpDTO;
import com.shinhan.emp.EmpService;

@WebServlet("/emp/detail.do")
public class EmpDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s_empId = request.getParameter("emp-id");
		if (s_empId == null) {
			return;
		}
		int empId = Integer.parseInt(s_empId);
		EmpService empService = new EmpService();
		EmpDTO emp = empService.selectByIdService(empId);
		request.setAttribute("emp", emp);
		RequestDispatcher rd = request.getRequestDispatcher("emp-detail.jsp");
		rd.forward(request, response);
//		주소 변경 없이 요청한 주소가 그대로 -> 내용만 위임한 페이지 출력
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		EmpDTO emp = makeEmp(request);
		EmpService empService = new EmpService();
		int result = empService.updateService(emp);
		System.out.println(result + "건 수정 완료");
		response.sendRedirect("list.do");
//		주소 값 자체를 변경해서 새로운 데이터 요청
	}

	private EmpDTO makeEmp(HttpServletRequest request) {
		String s_empId = request.getParameter("employeeId");
		int empId = s_empId.isEmpty() ? 0 : Integer.parseInt(s_empId);
		String s_managerId = request.getParameter("managerId");
		int managerId = s_managerId.isEmpty() ? 0 : Integer.parseInt(s_managerId);
		String s_deptId = request.getParameter("departmentId");
		int departmentId = s_deptId.isEmpty() ? 0 : Integer.parseInt(s_deptId);
		String s_salary = request.getParameter("salary");
		double salary = s_salary.isEmpty() ? 0 : Double.parseDouble(s_salary);
		String s_commission = request.getParameter("commissionPct");
		double commissionPct = s_commission.isEmpty() ? 0 : Double.parseDouble(s_commission);
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String jobId = request.getParameter("jobId");
		String s_hireDate = request.getParameter("hireDate");
		Date hireDate = s_hireDate.isEmpty() ? null : Date.valueOf(s_hireDate);
		return EmpDTO.builder().employeeId(empId).managerId(managerId).departmentId(departmentId).salary(salary)
				.commissionPct(commissionPct).firstName(firstName).lastName(lastName).email(email)
				.phoneNumber(phoneNumber).jobId(jobId).hireDate(hireDate).build();
	}
}
