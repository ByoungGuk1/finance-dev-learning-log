package com.shinhan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinhan.emp.EmpDTO;
import com.shinhan.emp.EmpService;

//		서버에서 실행되는 자바 프로그램
// == Controller
// 사용자 요청 -> controller -> service -> dao -> db
// 응답 <- jsp에 위임
/**
 * Servlet implementation class EmpListServlet
 * http://localhost:9999/appleproject/emp/list.do
 */
@WebServlet("/emp/list.do")
public class EmpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		서비스를 통해 비즈니스 로직을 수행한다
		EmpService empService = new EmpService();
		List<EmpDTO> empList = empService.selectAllService();
//		jsp에 넘겨줄 data를 저장한다.
		request.setAttribute("emplist", empList);
		RequestDispatcher rd = request.getRequestDispatcher("emplist.jsp");
		rd.forward(request, response);
	}
}
