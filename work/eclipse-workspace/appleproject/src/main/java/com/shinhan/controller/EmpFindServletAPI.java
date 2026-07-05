package com.shinhan.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shinhan.emp.EmpDTO;
import com.shinhan.emp.EmpService;

/**
 * Servlet implementation class EmpDetailServlet
 */
@WebServlet("/api/emp/find.do")
public class EmpFindServletAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println(request);

		EmpService empService = new EmpService();
		String s_deptId = request.getParameter("department-id");
		int deptId = s_deptId.isEmpty() ? 0 : Integer.valueOf(s_deptId);
		String jobId = request.getParameter("job-id");
		String s_salary = request.getParameter("salary");
		int salary = s_salary.isEmpty() ? 0 : Integer.valueOf(s_salary);
		String s_hireDate = request.getParameter("hire-date");
		Date hireDate = s_hireDate.isEmpty() ? null : Date.valueOf(s_hireDate);
		List<EmpDTO> result = empService.selectByConditionService(deptId, jobId, salary, hireDate);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(new Gson().toJson(result));
	}
}