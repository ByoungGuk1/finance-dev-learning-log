package com.shinhan.controller;

import java.io.IOException;

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
	}
}
