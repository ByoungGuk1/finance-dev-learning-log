<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- core 라서 주로 c 사용 -->
<!-- jsp 주석 -->
<!-- taglib는 라이브러리가 있어야 사용 가능 (WEB-INF/lib/*.jar) -->
<!-- EL은 조건문, 반복문이 없음 -->
<!-- JSTL 사용 : 조건문과 반복문이 있음 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empList</title>
</head>
<body>
	<table border="1">
		<caption>모든 직원의 목록</caption>
		<thead>
			<tr>
				<th>직원번호</th>
				<th>fname</th>
				<th>lname</th>
				<th>email</th>
				<th>phone</th>
				<th>hiredate</th>
				<th>job</th>
				<th>salary</th>
				<th>comm</th>
				<th>manager</th>
				<th>dept</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emp" items="${emplist}">
				<tr>
					<td><a href="detail.do?emp-id=${emp.employeeId}">${emp.employeeId}</a></td>
					<td>${emp.firstName}</td>
					<td>${emp.lastName}</td>
					<td>${emp.email}</td>
					<td>${emp.phoneNumber}</td>
					<td>${emp.hireDate}</td>
					<td>${emp.jobId}</td>
					<td>${emp.salary}</td>
					<td>${emp.commissionPct}</td>
					<td>${emp.managerId}</td>
					<td>${emp.departmentId}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="11">hr계정의 Employees Datas</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>