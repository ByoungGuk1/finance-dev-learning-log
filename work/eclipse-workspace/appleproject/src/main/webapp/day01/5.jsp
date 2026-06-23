<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// java source 영역
String myName = "홍길동";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사용자 정보</h1>
	<h2>
		이름 :
		<%=myName%></h2>
	<!-- el 문법 -->
	<h2>이메일 : ${email}</h2>
	<h2>주소 : ${address}</h2>
</body>
</html>