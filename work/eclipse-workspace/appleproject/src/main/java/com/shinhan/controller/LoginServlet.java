package com.shinhan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/auth/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

//		GET : 요청의 url을 통해 값을 전달, 자동으로 encoding,decoding
//		POST : 요청 문서의 Body에 들어온다. Encoding, Decoding 필요 -> 한글이 깨질 수 있음.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String s_id = request.getParameter("user_id");
//		String s_pw = request.getParameter("user_pw");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<meta http-equiv='refresh' content='3;../emp/list.do' />");
		out.print("<h1>로그인 결과</h1>");
		out.print("<h2>" + s_id + " 님 환영합니다.</h2>");
	}
}
