package com.shinhan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shinhan.department.DepartmentDTO;
import com.shinhan.department.DepartmentService;

//		서버에서 실행되는 자바 프로그램
// == Controller
// 사용자 요청 -> controller -> service -> dao -> db
// 응답 <- jsp에 위임
/**
 * Servlet implementation class EmpListServlet
 * http://localhost:9999/appleproject/emp/list.do
 */
@WebServlet("/api/emp/dept-list.do")
public class DeptListServletAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DepartmentService deptService = new DepartmentService();
		List<DepartmentDTO> result = deptService.selectAll();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(new Gson().toJson(result));
	}
}
