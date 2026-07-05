package com.shinhan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinhan.emp.EmpService;

/**
 * Servlet implementation class EmpDeleteServlet
 */
@WebServlet("/api/emp/delete.do")
public class EmpDeleteServletAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String s_emplId = request.getParameter("emp-id");
		int empId = s_emplId.isEmpty() ? 0 : Integer.parseInt(s_emplId);

		EmpService empService = new EmpService();
		int result = empService.deleteService(empId);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String json = "{\"result\":" + result + "}";
		response.getWriter().print(json);
	}
}
