package com.shinhan.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shinhan.emp.EmpDTO;
import com.shinhan.emp.EmpService;

@WebServlet("/api/emp/detail.do")
public class EmpDetailServletAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s_empId = request.getParameter("emp-id");
		String s_firstName = request.getParameter("first-name");
		System.out.println(s_empId + " || " + s_firstName);
		if (s_empId.isEmpty() && s_firstName.isEmpty()) {
			return;
		}

		EmpService empService = new EmpService();
		EmpDTO emp = null;
		if (!s_empId.isEmpty()) {
			int empId = Integer.parseInt(s_empId);
			emp = empService.selectByIdService(empId);
		}
		if (!s_firstName.isEmpty()) {
			emp = empService.selectByName(s_firstName);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(new Gson().toJson(emp));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		EmpDTO emp = makeEmp(request);
		EmpService empService = new EmpService();
		int result = empService.updateService(emp);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String json = "{\"result\":" + result + "}";
		response.getWriter().print(json);
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
